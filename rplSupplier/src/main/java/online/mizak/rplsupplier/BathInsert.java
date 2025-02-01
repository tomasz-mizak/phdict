package online.mizak.rplsupplier;

import feign.FeignException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class BathInsert {

    @Value("${rpl.overall.url}")
    private String overallUrl;

    private final PhDictApi phDictApi;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    BathInsert(PhDictApi phDictApi) {
        this.phDictApi = phDictApi;
    }

    void run() {
        log.info("# - - - Starting batch insert - - - #");

        try {
            var file = FileDownloader.downloadFileToTemp(overallUrl);
            log.info("# - - - Source file has been downloaded - - - #");
            log.info("Total size: {}", FileDownloader.humanReadableFileSize(file.length()));

            var products = RplDataReader.read(file);
            log.info("Total products: {}", products.size());

            int batchSize = 1000;
            int totalProducts = products.size();

            List<CompletableFuture<Void>> futures = new ArrayList<>();

            for (int i = 0; i < totalProducts; i += batchSize) {
                List<CreateDictionaryProduct> batch = products.subList(i, Math.min(i + batchSize, totalProducts));

                /**
                 * CompletableFuture.supplyAsync(() -> {
                 *     // długotrwała operacja
                 *     return "Wynik";
                 * }).thenApply(result -> {
                 *     // przetwarzanie wyniku
                 *     return result + " przetworzony";
                 * }).thenAccept(System.out::println)
                 *   .exceptionally(ex -> {
                 *       System.err.println("Błąd: " + ex.getMessage());
                 *       return null;
                 *   });
                 */

                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        phDictApi.createDictionaryProducts(batch).join();
                }, executorService);
                futures.add(future);

            }
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            log.info("Upload finished.");

            boolean isDeleted = file.delete();
            if (isDeleted) {
                log.info("Deleted file: {}", file);
            }

        } catch (IOException e) {
            log.error("Error during processing: {}", e.getMessage());
        }
    }

    private String formatTime(long elapsedTimeMillis) {
        long seconds = (elapsedTimeMillis / 1000) % 60;
        long minutes = (elapsedTimeMillis / 1000) / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
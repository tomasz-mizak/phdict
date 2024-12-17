package online.mizak.rplsupplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
class BathInsert {

    @Value("${rpl.overall.url}")
    private String overallUrl;

    private final PhDictApi phDictApi;

    BathInsert(PhDictApi phDictApi) {
        this.phDictApi = phDictApi;
    }

    @PostConstruct
    void init() {
        run();
    }

    void run() {
        log.info("# Schedule");

        var mapper = new ObjectMapper();

        try {
            var file = FileDownloader.downloadFileToTemp(overallUrl);
            log.info("# - - - File has been downloaded - - - #");
            log.info("# Total size: {}", FileDownloader.humanReadableFileSize(file.length()));

            var products = RplDataReader.read(file);
            log.info("# Total products: {}", products.size());

            int batchSize = 2000;
            int totalProducts = products.size();
            int productCounter = 0;
            int successCounter = 0;
            int failCounter = 0;
            int barLength = 50;

            long startTime = System.currentTimeMillis();
            List<ErrorDetails> errorDetailsList = new ArrayList<>();

            // Dzielenie produktów na paczki
            for (int i = 0; i < totalProducts; i += batchSize) {
                List<CreateDictionaryProduct> batch = products.subList(i, Math.min(i + batchSize, totalProducts));

                try {
                    // Wysłanie paczki produktów do drugiej aplikacji
                    phDictApi.createDictionaryProducts(batch);
                    successCounter += batch.size();
                } catch (FeignException.FeignClientException feignClientException) {
                    failCounter += batch.size();
                    log.error("Batch failed: {}", feignClientException.getMessage()); // todo, sprawdzić czy jak wyjebie to nie powinno przyjąc całego batcha!
                }

                productCounter += batch.size();
                int progress = (int) ((productCounter / (double) totalProducts) * barLength);
                String progressBar = "=".repeat(progress) + " ".repeat(barLength - progress);

                long elapsedTime = System.currentTimeMillis() - startTime;
                String timer = formatTime(elapsedTime);

                System.out.printf("\rProgress: [%s] %d%% (%d/%d) (success: %d/ fail: %d) Time Elapsed: %s",
                        progressBar, (int) ((productCounter / (double) totalProducts) * 100),
                        productCounter, totalProducts, successCounter, failCounter, timer);
            }
            System.out.println();
            log.info("# Finished adding all products to phdict.");

            if (failCounter > 0) {
                log.info("# Number of failed products: {}", failCounter);
            }

            boolean isDeleted = file.delete();
            if (isDeleted) {
                log.info("# Deleted file: {}", file);
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
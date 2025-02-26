package online.mizak.rplsupplier;

import lombok.extern.slf4j.Slf4j;
import online.mizak.rplsupplier.dto.CreateDictionaryProduct;
import online.mizak.rplsupplier.utils.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class BathInsert {

    @Value("${rpl.overall.url}")
    private String overallUrl;

    private final PhDictApi phDictApi;

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

            for (int i = 0; i < totalProducts; i += batchSize) {
                List<CreateDictionaryProduct> batch = products.subList(i, Math.min(i + batchSize, totalProducts));

                var bulkImportReport = phDictApi.createDictionaryProducts(batch, "RPL");
                log.info(JSON.stringify(bulkImportReport));

            }

            log.info("Upload finished.");

            boolean isDeleted = file.delete();
            if (isDeleted) {
                log.info("Deleted file: {}", file);
            }

        } catch (IOException e) {
            log.error("Error during processing: {}", e.getMessage());
        }
    }

}
package online.mizak.rplsupplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
class Scheduler {

    @Value("${rpl.overall.url}")
    private String overallUrl;

    private final PhDictApi phDictApi;

    Scheduler(PhDictApi phDictApi) {
        this.phDictApi = phDictApi;
    }

    @Scheduled(cron = "0 */1 * * * *")
    void run() {
        log.info("# Schedule");

        var mapper = new ObjectMapper();

        try {
            var file = FileDownloader.downloadFileToTemp(overallUrl);
            log.info("# - - - File has been downloaded - - - #");
            log.info("# Download File: {}", file);
            log.info("# From URL: {}", overallUrl);
            log.info("# Total size: {}", FileDownloader.humanReadableFileSize(file.length()));

            var products = RplDataReader.read(file);
            log.info("# - - - Rpl data reader - - - #");
            log.info("# Total products: {}", products.size());

            log.info("# - - - Adding products to phdict - - - #");
            for (CreateDictionaryProduct product : products) {
                try {
                    var result = phDictApi.createDictionaryProduct(product);
                } catch (FeignException.FeignClientException feignClientException) {
                    var errorDetails = mapper.readValue(feignClientException.contentUTF8(), ErrorDetails.class);
                    if (!errorDetails.code().equals("PRODUCT_ALREADY_EXISTS")) {
                        log.error("Unhandled exception has been occurred '{}' for product '{}'.", errorDetails, product);
                    }
                }
            }

            var isDeleted = file.delete();
            if (isDeleted) {
                log.info("# Deleted file: {}", file);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

}

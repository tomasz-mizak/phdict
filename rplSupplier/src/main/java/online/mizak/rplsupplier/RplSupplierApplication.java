package online.mizak.rplsupplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class RplSupplierApplication implements CommandLineRunner {

    private final BathInsert bathInsert;

    public RplSupplierApplication(BathInsert bathInsert) {
        this.bathInsert = bathInsert;
    }

    public static void main(String[] args) {
        SpringApplication.run(RplSupplierApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            bathInsert.run();
        } catch (Exception e) {
            log.error("An exception occurred on instant run.", e);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void schedule() {
        try {
            bathInsert.run();
        } catch (Exception e) {
            log.error("An exception occurred on scheduled run.", e);
        }
    }

}

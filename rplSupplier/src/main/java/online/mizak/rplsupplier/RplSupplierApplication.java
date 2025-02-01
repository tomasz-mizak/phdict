package online.mizak.rplsupplier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

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
    public void run(String... args) throws Exception {
        bathInsert.run();
    }
}

package online.mizak.rplsupplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class Scheduler {

    @Scheduled(cron = "0 */1 * * * *")
    void run() {
        log.info("# Schedule");

    }

}

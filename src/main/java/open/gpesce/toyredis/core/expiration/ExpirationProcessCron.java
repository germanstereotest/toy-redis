package open.gpesce.toyredis.core.expiration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExpirationProcessCron {

    @Scheduled(cron="*/1 * * * * *")
    public void cleanExpiredKeys() {
        log.trace("Cleaning keys by TTL");
        // usando el mapa acceder a las TTLs expiradasl
    }

}

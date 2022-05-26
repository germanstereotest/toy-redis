package open.gpesce.toyredis.core.expiration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisKeyWithTTL;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@AllArgsConstructor
public class ExpirationProcessCron {

    private final ConcurrentHashMap<ToyRedisKey, ToyRedisValue> dataCollection;

    @Scheduled(cron="*/1 * * * * *")
    public void cleanExpiredKeys() {
        log.trace("Cleaning keys by TTL");
        // usando el mapa acceder a las TTLs expiradasl
        dataCollection.entrySet().parallelStream()
            .filter(itemMap -> itemMap.getKey().getTtlExpiration() != -1)
            .forEach(itemMap -> {
                var ttlKey = (ToyRedisKeyWithTTL) itemMap.getKey();
                if (Instant.now(Clock.system(ZoneId.of("Europe/Madrid")))
                        .isAfter( ttlKey.getCreationInstant().plus(ttlKey.getTtl(), ChronoUnit.SECONDS))  ) {
                    dataCollection.remove(itemMap.getKey());
                }
            });
    }

}

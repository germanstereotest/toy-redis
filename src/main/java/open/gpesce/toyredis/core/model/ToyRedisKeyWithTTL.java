package open.gpesce.toyredis.core.model;

import lombok.Data;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Data
public abstract class ToyRedisKeyWithTTL implements ToyRedisKey{
    private long ttl = -1;
    private Instant creationInstant = Instant.now(Clock.system(ZoneId.of("Europe/Madrid")));

    @Override
    public long getTtlExpiration() {
        return ttl;
    }

    @Override
    public void setTtlExpiration(long ttl) {
        this.ttl = ttl;
    }
}

package open.gpesce.toyredis.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
public class ToyRedisKeyImpl implements ToyRedisKey{
    private Object key;
    @EqualsAndHashCode.Exclude
    private long ttl = -1;
    @EqualsAndHashCode.Exclude
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

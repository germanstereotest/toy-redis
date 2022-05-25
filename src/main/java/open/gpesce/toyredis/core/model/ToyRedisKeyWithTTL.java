package open.gpesce.toyredis.core.model;

import lombok.Data;

@Data
public abstract class ToyRedisKeyWithTTL implements ToyRedisKey{
    private long ttl = -1;
}

package open.gpesce.toyredis.core.model;

public interface ToyRedisKey {
    void setTtlExpiration(long ttl);
    long getTtlExpiration();
}

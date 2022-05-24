package open.gpesce.toyredis.core;

public interface ToyRedis<K extends ToyRedisKey, V extends ToyRedisValue> {

    V SET(K key, V value);
    V SET(K key, V value, long ex);
    V GET(K key);
    V DEL(K key);
    V DBSIZE(K key);
    V INC(K key);

    V ZADD(K key, int score, String member);
    V ZCARD(K key);
    V ZRANK(K key, String member);
    V ZRANGE(K key, int start, int stop);
}

package open.gpesce.toyredis.core.service;

import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;

public interface ToyRedis<K extends ToyRedisKey, V extends ToyRedisValue> {

    V SET(K key, V value, long ex);
    V GET(K key);
    void DEL(K key);
    int DBSIZE();
    V INCR(K key);

    V ZADD(K key, int score, String member);
    V ZCARD(K key);
    V ZRANK(K key, String member);
    V ZRANGE(K key, int start, int stop);
}

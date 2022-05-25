package open.gpesce.toyredis.api.service;

import lombok.AllArgsConstructor;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.service.ToyRedis;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("toyRedisApi")
@Qualifier("toyRedisApi")
@AllArgsConstructor
public class ToyRedisApiImpl implements ToyRedis {

    private final ToyRedis<ToyRedisKey, ToyRedisValue> toyRedisCore;

    @Override
    public ToyRedisValue SET(ToyRedisKey key, ToyRedisValue value, long ex) {
        return toyRedisCore.SET(key, value, ex);
    }

    @Override
    public ToyRedisValue GET(ToyRedisKey key) {
        return toyRedisCore.GET(key);
    }

    @Override
    public void DEL(ToyRedisKey key) {
        toyRedisCore.DEL(key);
    }

    @Override
    public int DBSIZE() {
        return toyRedisCore.DBSIZE();
    }

    @Override
    public ToyRedisValue INCR(ToyRedisKey key) {
        return toyRedisCore.INCR(key);
    }

    @Override
    public ToyRedisValue ZADD(ToyRedisKey key, int score, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    public ToyRedisValue ZCARD(ToyRedisKey key) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    public ToyRedisValue ZRANK(ToyRedisKey key, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    public ToyRedisValue ZRANGE(ToyRedisKey key, int start, int stop) {
        throw new RuntimeException("Command not implemented");
    }
}

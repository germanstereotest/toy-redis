package open.gpesce.toyredis.core.service;

import lombok.AllArgsConstructor;
import open.gpesce.toyredis.core.data.ToyRedisCollection;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import open.gpesce.toyredis.core.validator.KeyValidator;
import open.gpesce.toyredis.core.validator.ValueValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("toyRedisCore")
@Qualifier("toyRedisCore")
@AllArgsConstructor
public class ToyRedisCoreImpl implements ToyRedis {

    private final ToyRedisCollection<ToyRedisKey, ToyRedisValue> repository;
    private final KeyValidator keyValidator;
    private final ValueValidator valueValidator;

    @Override

    public ToyRedisValue SET(ToyRedisKey key, ToyRedisValue value, long ex) {
        keyValidator.accept(key);
        valueValidator.accept(value);
        key.setTtlExpiration(ex);
        return repository.save(key, value);
    }

    @Override
    public ToyRedisValue GET(ToyRedisKey key) {
        keyValidator.accept(key);
        return repository.find(key);
    }

    @Override
    public void DEL(ToyRedisKey key) {
        keyValidator.accept(key);
        repository.delete(key);
    }

    @Override
    public int DBSIZE() {
        return repository.count();
    }

    @Override
    public ToyRedisValue INCR(ToyRedisKey key) {
        keyValidator.accept(key);
        ToyRedisValueImpl element = (ToyRedisValueImpl) repository.find(key);
        if (element == null) {
            throw new IllegalArgumentException("Key " + key + " not found");
        }
        try {
            //FIXME: ver de que tipo es y demas List, Set, Hash
            var intValue = Integer.parseInt(element.getValue().toString());
            Integer incremental = intValue + 1;
            element.setValue(incremental.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The key " + element.getValue() + " has not a number value");
        }
        return repository.save(key, element);
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

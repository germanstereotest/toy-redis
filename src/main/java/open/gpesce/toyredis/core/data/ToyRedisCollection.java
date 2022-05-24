package open.gpesce.toyredis.core.data;

import open.gpesce.toyredis.core.ToyRedisKey;
import open.gpesce.toyredis.core.ToyRedisValue;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ToyRedisCollection<ToyRedisCliKey, ToyRedisCliValue> {

    ConcurrentHashMap<ToyRedisKey, ToyRedisValue> dataCollection;

    public ToyRedisValue save(ToyRedisKey key, ToyRedisValue value) {
        dataCollection.put(key, value);
        return dataCollection.get(key);
    }

    public ToyRedisValue find(ToyRedisKey key) {
        return dataCollection.get(key);
    }

}

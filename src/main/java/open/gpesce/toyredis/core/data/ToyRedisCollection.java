package open.gpesce.toyredis.core.data;

import lombok.AllArgsConstructor;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class ToyRedisCollection<K extends ToyRedisKey, V extends ToyRedisValue> {

    private final ConcurrentHashMap<K, V> dataCollection;

    public V save(K key, V value) {
        dataCollection.put(key, value);
        return dataCollection.get(key);
    }

    public V find(K key) {
        return dataCollection.get(key);
    }

    public void delete(K key) {
        dataCollection.remove(key);
    }

    public int count() {
        return dataCollection.size();
    }

}

package open.gpesce.toyredis.core.data;

import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CollectionConfig {

    @Bean
    public ConcurrentHashMap<ToyRedisKey, ToyRedisValue> dataCollection() {
        return new ConcurrentHashMap<>();
    }

}

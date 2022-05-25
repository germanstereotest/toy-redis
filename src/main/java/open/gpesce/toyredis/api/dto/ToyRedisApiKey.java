package open.gpesce.toyredis.api.dto;

import lombok.Data;
import open.gpesce.toyredis.core.model.ToyRedisKey;

@Data
public class ToyRedisApiKey implements ToyRedisKey {
    private String key;
}

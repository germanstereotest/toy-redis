package open.gpesce.toyredis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import open.gpesce.toyredis.core.model.ToyRedisKeyWithTTL;

@Data
@AllArgsConstructor
public class ToyRedisApiKey extends ToyRedisKeyWithTTL {
    private String key;
}

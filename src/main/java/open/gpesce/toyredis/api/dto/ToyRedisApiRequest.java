package open.gpesce.toyredis.api.dto;

import lombok.Data;

@Data
public class ToyRedisApiRequest {
    private ToyRedisApiKey key;
    private ToyRedisApiValue value;
}

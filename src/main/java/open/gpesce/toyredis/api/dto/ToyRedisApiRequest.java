package open.gpesce.toyredis.api.dto;

import lombok.Data;

@Data
public class ToyRedisApiRequest {
    private Object key;
    private Object value;
    private long ttl;
}

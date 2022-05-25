package open.gpesce.toyredis.cli.model;

import lombok.Builder;
import lombok.Data;
import open.gpesce.toyredis.core.model.ToyRedisKeyWithTTL;

@Data
@Builder
public class ToyRedisCliKey extends ToyRedisKeyWithTTL {
    private String key;
}

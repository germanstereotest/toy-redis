package open.gpesce.toyredis.cli.model;

import lombok.Builder;
import lombok.Data;
import open.gpesce.toyredis.core.ToyRedisKey;

@Data
@Builder
public class ToyRedisCliKey implements ToyRedisKey {
    private String key;
}

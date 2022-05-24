package open.gpesce.toyredis.cli.model;

import lombok.Builder;
import lombok.Data;
import open.gpesce.toyredis.core.ToyRedisValue;

@Data
@Builder
public class ToyRedisCliValue implements ToyRedisValue {
    private String value;
}

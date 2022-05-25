package open.gpesce.toyredis.api.dto;

import lombok.Data;
import open.gpesce.toyredis.core.model.ToyRedisValue;
@Data
public class ToyRedisApiValue implements ToyRedisValue {
    private Object value;

    public String toString(){
        return value.toString();
    }
}

package open.gpesce.toyredis.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ToyRedisValueImpl implements ToyRedisValue {
    private Object value;

    public String toString(){
        return value.toString();
    }
}

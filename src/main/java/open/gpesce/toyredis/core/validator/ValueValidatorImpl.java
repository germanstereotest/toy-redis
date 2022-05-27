package open.gpesce.toyredis.core.validator;

import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import org.springframework.stereotype.Component;

@Component
public class ValueValidatorImpl implements ValueValidator {

    @Override
    public void accept(ToyRedisValue value) {
        if (!(value instanceof ToyRedisValueImpl)) {
            throw new IllegalArgumentException("Invalid Value");
        } else {
            var toyRedisKey = (ToyRedisValueImpl) value;
            if (toyRedisKey.getValue() == null) {
                throw new IllegalArgumentException("Invalid Value");
            }

        }
    }
}

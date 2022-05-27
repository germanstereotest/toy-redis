package open.gpesce.toyredis.core.validator;

import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisKeyImpl;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import org.springframework.stereotype.Component;

@Component
public class KeyValidatorImpl implements KeyValidator {

    @Override
    public void accept(ToyRedisKey key) {
        if (!(key instanceof ToyRedisKeyImpl)) {
            throw new IllegalArgumentException("Invalid Key");
        } else {
            var toyRedisKey = (ToyRedisKeyImpl) key;
            if (toyRedisKey.getKey() == null ) {
                throw new IllegalArgumentException("Invalid Key");
            }

        }
    }

}

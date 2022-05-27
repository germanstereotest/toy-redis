package open.gpesce.toyredis.core.validator;

import open.gpesce.toyredis.core.model.ToyRedisValue;

import java.util.function.Consumer;

@FunctionalInterface
public interface ValueValidator extends Consumer<ToyRedisValue> {
}

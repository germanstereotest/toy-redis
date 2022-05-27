package open.gpesce.toyredis.core.validator;

import open.gpesce.toyredis.core.model.ToyRedisKey;

import java.util.function.Consumer;

@FunctionalInterface
public interface KeyValidator extends Consumer<ToyRedisKey> {
}

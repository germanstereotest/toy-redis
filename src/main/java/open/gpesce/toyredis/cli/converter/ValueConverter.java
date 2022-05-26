package open.gpesce.toyredis.cli.converter;

import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ValueConverter implements Converter<String, ToyRedisValue> {
    @Override
    public ToyRedisValueImpl convert(String source) {
        return ToyRedisValueImpl.builder().value(source).build();
    }

}

package open.gpesce.toyredis.cli.converter;

import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisKeyImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KeyConverter implements Converter<String, ToyRedisKey> {
    @Override
    public ToyRedisKey convert(String source) {
        return ToyRedisKeyImpl.builder().key(source).build();
    }

}

package open.gpesce.toyredis.cli;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import open.gpesce.toyredis.core.ToyRedis;
import open.gpesce.toyredis.core.ToyRedisKey;
import open.gpesce.toyredis.core.ToyRedisValue;
import open.gpesce.toyredis.core.data.ToyRedisCollection;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@Slf4j
@AllArgsConstructor
public class ToyRedisCliImpl<ToyRedisCliKey, ToyRedisCliValue> implements ToyRedis {

    private final ToyRedisCollection repository;

    @Override
    @ShellMethod(key = "SET", value = "save")
    public ToyRedisValue SET(ToyRedisKey key, ToyRedisValue value) {
        return repository.save(key, value);
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue SET(ToyRedisKey key, ToyRedisValue value, long ex) {
        return null;
    }

    @Override
    @ShellMethod(key = "GET", value = "find")
    public ToyRedisValue GET(ToyRedisKey key) {
        return repository.find(key);
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue DEL(ToyRedisKey key) {
        return null;
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue DBSIZE(ToyRedisKey key) {
        return null;
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue INC(ToyRedisKey key) {
        return null;
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue ZADD(ToyRedisKey key, int score, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue ZCARD(ToyRedisKey key) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue ZRANK(ToyRedisKey key, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod("función de suma")
    public ToyRedisValue ZRANGE(ToyRedisKey key, int start, int stop) {
        throw new RuntimeException("Command not implemented");
    }

}

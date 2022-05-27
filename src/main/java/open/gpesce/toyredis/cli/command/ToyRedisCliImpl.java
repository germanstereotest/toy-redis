package open.gpesce.toyredis.cli.command;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import open.gpesce.toyredis.core.service.ToyRedis;
import open.gpesce.toyredis.core.model.ToyRedisKey;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent("toyRedisCli")
@Slf4j
@AllArgsConstructor
@Qualifier("toyRedisCli")
public class ToyRedisCliImpl implements ToyRedis {

    private final ToyRedis<ToyRedisKey, ToyRedisValue> toyRedisCore;

    @Override
    @ShellMethod(key = "SET", value = "add an element with optional expiration in seconds")
    public ToyRedisValue SET(ToyRedisKey key, ToyRedisValue value, @ShellOption(defaultValue = "-1") long ex) {
        return toyRedisCore.SET(key, value, ex);
    }

    @Override
    @ShellMethod(key = "GET", value = "find an element by key")
    public ToyRedisValue GET(ToyRedisKey key) {
        return toyRedisCore.GET(key);
    }

    @Override
    @ShellMethod(key = "DEL", value = "delete an element by key")
    public void DEL(ToyRedisKey key) {
        toyRedisCore.DEL(key);
    }

    @Override
    @ShellMethod(key = "DBSIZE", value = "count the number of keys")
    public int DBSIZE() {
        return toyRedisCore.DBSIZE();
    }

    @Override
    @ShellMethod(key = "INCR", value = "increment a value (numeric) by key")
    public ToyRedisValue INCR(ToyRedisKey key) {
        return toyRedisCore.INCR(key);
    }


    @Override
    @ShellMethod(key = "ZADD", value = "TO BE IMPLEMENTED")
    public ToyRedisValue ZADD(ToyRedisKey key, int score, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod(key = "ZCARD", value = "TO BE IMPLEMENTED")
    public ToyRedisValue ZCARD(ToyRedisKey key) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod(key = "ZRANK", value = "TO BE IMPLEMENTED")
    public ToyRedisValue ZRANK(ToyRedisKey key, String member) {
        throw new RuntimeException("Command not implemented");
    }

    @Override
    @ShellMethod(key = "ZRANGE", value = "TO BE IMPLEMENTED")
    public ToyRedisValue ZRANGE(ToyRedisKey key, int start, int stop) {
        throw new RuntimeException("Command not implemented");
    }

}

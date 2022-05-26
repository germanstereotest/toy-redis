package open.gpesce.toyredis.api.controller;

import lombok.AllArgsConstructor;
import open.gpesce.toyredis.api.dto.ToyRedisApiRequest;
import open.gpesce.toyredis.core.model.ToyRedisKeyImpl;
import open.gpesce.toyredis.core.model.ToyRedisValue;
import open.gpesce.toyredis.core.model.ToyRedisValueImpl;
import open.gpesce.toyredis.core.service.ToyRedis;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class ToyRedisApiController {

    private final ToyRedis toyRedisApi;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ToyRedisValue getValueFromKey(@RequestParam(required = true) String key) {
        return toyRedisApi.GET(ToyRedisKeyImpl.builder().key(key).build());
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ToyRedisValue setValueFromKey(@RequestBody(required = true) ToyRedisApiRequest request) {
        return toyRedisApi.SET(
                ToyRedisKeyImpl.builder().key(request.getKey()).build(),
                ToyRedisValueImpl.builder().value(request.getValue()).build(),
                request.getTtl());
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@RequestParam(required = true) String key) {
        toyRedisApi.DEL(ToyRedisKeyImpl.builder().key(key).build());
    }

    @GetMapping(value = {"/dbsize"})
    @ResponseStatus(HttpStatus.OK)
    public int getDBSize() {
        return toyRedisApi.DBSIZE();
    }

    @PutMapping(value = {"/incr"})
    @ResponseStatus(HttpStatus.OK)
    public ToyRedisValue increment(@RequestParam(required = true) String key) {
        return toyRedisApi.INCR(ToyRedisKeyImpl.builder().key(key).build());
    }
}

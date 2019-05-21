package xyz.fz.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key,
                      @PathVariable("value") String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }

    @RequestMapping("/hset/{key}/{field}/{value}")
    public String hset(@PathVariable("key") String key,
                       @PathVariable("field") String field,
                       @PathVariable("value") String value) {
        redisTemplate.opsForHash().put(key, field, value);
        return "ok";
    }

    @RequestMapping("/hgetall/{key}")
    public String hgetall(@PathVariable("key") String key) {
        return String.valueOf(redisTemplate.opsForHash().entries(key));
    }

    @RequestMapping("/hget/{key}/{field}")
    public String hget(@PathVariable("key") String key,
                       @PathVariable("field") String field) {
        return String.valueOf(redisTemplate.opsForHash().get(key, field));
    }
}

package app.ordering.food.service.impl;

import app.ordering.food.service.RedisService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void flushDb() {
        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return "db cleared";
        });
    }

    @Override
    public List<String> getKeys() {
        Set<String> keys = stringRedisTemplate.keys("*");
        if (keys == null) {
            return null;
        }
        return new ArrayList<>(keys);
    }

    @Override
    public boolean updateWithTtl(String key, String val, long ttl) {
        if (key == null || val == null) {
            return false;
        }
        if (ttl < 0) {
            return false;
        }
        if (ttl == 0) {
            stringRedisTemplate.opsForValue().set(key, val);
        } else {
            stringRedisTemplate.opsForValue().set(key, val, ttl, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public boolean update(String key, String val) {
        return updateWithTtl(key, val, 0);
    }

    @Override
    public String get(String key) {
        if (key == null) {
            return null;
        }
        Boolean found = stringRedisTemplate.hasKey(key);
        if (found == null) {
            return null;
        }
        if (!found) {
            return null;
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean delete(String key) {
        if (key == null) {
            return false;
        }
        Boolean del = stringRedisTemplate.delete(key);
        if (del == null) {
            return false;
        }
        return del;
    }
}

package app.ordering.food.service.impl;

import app.ordering.food.service.RedisService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("redisService")
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void flushDb() {
        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.flushDb();
            return "db cleared";
        });
    }

    @Override
    public List<String> getKeys() {
        Set<String> keys = redisTemplate.keys("*");
        if (keys == null) {
            return null;
        }
        return new ArrayList<>(keys);
    }

    @Override
    public boolean updateStringWithTtl(String key, String val, long ttl) {
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
    public boolean updateString(String key, String val) {
        return updateStringWithTtl(key, val, 0);
    }

    @Override
    public String getString(String key) {
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

    @Override
    public <T> boolean updateObjectWithTtl(String key, T val, long ttl) {
        if (key == null || val == null) {
            return false;
        }
        if (ttl < 0) {
            return false;
        }
        if (ttl == 0) {
            redisTemplate.opsForValue().set(key, val);
        } else {
            redisTemplate.opsForValue().set(key, val, ttl, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public <T> boolean updateObject(String key, T val) {
        return updateObjectWithTtl(key, val, 0);
    }

    @Override
    public <T> T getObject(String key) {
        if (key == null) {
            return null;
        }
        Boolean found = redisTemplate.hasKey(key);
        if (found == null) {
            return null;
        }
        if (!found) {
            return null;
        }
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }
}

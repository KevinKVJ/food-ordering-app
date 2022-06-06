package app.ordering.food.service.impl;

import app.ordering.food.common.Result;
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
            return "ok";
        });
    }

    @Override
    public Result<List<String>> getKeys() {
        Set<String> keys = stringRedisTemplate.keys("*");
        if (keys == null) {
            return Result.error("004M001", "redis获取键失败");
        }
        List<String> result = new ArrayList<>(keys);
        return Result.success(result, "redis获取键成功");
    }

    @Override
    public Result<Void> updateWithTtl(String key, String val, long timeout) {
        if (key == null) {
            return Result.error("004P001", "redis键为null");
        }
        if (val == null) {
            return Result.error("004P002", "redis值为null");
        }
        stringRedisTemplate.opsForValue().set(key, val, timeout, TimeUnit.SECONDS);
        return Result.success("redis键值插入成功");
    }

    @Override
    public Result<Void> update(String key, String val) {
        if (key == null) {
            return Result.error("004P003", "redis键为null");
        }
        if (val == null) {
            return Result.error("004P004", "redis值为null");
        }
        stringRedisTemplate.opsForValue().set(key, val);
        return Result.success("redis键值插入成功");
    }

    @Override
    public Result<String> get(String key) {
        if (key == null) {
            return Result.error("004P005", "redis键为null");
        }
        Boolean found = stringRedisTemplate.hasKey(key);
        if (found == null) {
            return Result.error("004P006", "redis获取键失败");
        }
        if (!found) {
            return Result.error("004B001", "redis键不存在");
        }
        return Result.success(stringRedisTemplate.opsForValue().get(key), "redis值获取成功");
    }

    @Override
    public Result<Void> delete(String key) {
        if (key == null) {
            return Result.error("004P007", "redis键为null");
        }
        Boolean found = stringRedisTemplate.hasKey(key);
        if (found == null) {
            return Result.error("004P008", "redis获取键失败");
        }
        if (!found) {
            return Result.error("004B002", "redis键不存在");
        }
        Boolean del = stringRedisTemplate.delete(key);
        if (del == null) {
            return Result.error("004P009", "redis删除键失败");
        }
        return Result.success("redis删除键成功");
    }
}

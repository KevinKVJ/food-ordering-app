package app.ordering.food.service;

import app.ordering.food.common.Result;

import java.util.List;

public interface RedisService {
    void flushDb();
    Result<List<String>> getKeys();
    Result<Void> updateWithTtl(String key, String val, long timeout);
    Result<Void> update(String key, String val);
    Result<String> get(String key);
    Result<Void> delete(String key);
}

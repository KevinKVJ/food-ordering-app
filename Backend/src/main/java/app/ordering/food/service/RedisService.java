package app.ordering.food.service;

import java.util.List;

public interface RedisService {
    void flushDb();
    List<String> getKeys();
    boolean updateWithTtl(String key, String val, long ttl);
    boolean update(String key, String val);
    String get(String key);
    boolean delete(String key);
}

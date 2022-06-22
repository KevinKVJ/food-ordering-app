package app.ordering.food.service;

import java.util.List;

public interface RedisService {
    void flushDb();
    List<String> getKeys();

    boolean updateStringWithTtl(String key, String val, long ttl);
    boolean updateString(String key, String val);
    String getString(String key);
    boolean delete(String key);

    <T> boolean updateObjectWithTtl(String key, T val, long ttl);
    <T> boolean updateObject(String key, T val);
    <T> T getObject(String key);
}

package app.ordering.food.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceTest {

    @Resource
    private RedisService redisService;

    @Test
    void getKeys() {
        redisService.update("key1", "val1");
        redisService.update("key2", "val2");
        redisService.update("key3", "val3");
        redisService.update("key4", "val4");
        List<String> result = redisService.getKeys();
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void updateTest() {
        assertTrue(redisService.update("key1", ""));
        assertNull(redisService.get("key2"));
    }

    @Test
    void updateTest2() {
        assertTrue(redisService.updateWithTtl("key3", "", 1000));
    }

    @Test
    void deleteTest1() {
        assertFalse(redisService.delete("testtesttesttest"));
    }

    @Test
    void deleteTest2() {
        assertTrue(redisService.update("testtesttesttestX", ""));
        assertTrue(redisService.delete("testtesttesttestX"));
    }

    @AfterEach
    public void flushDb() {
        redisService.flushDb();
    }
}
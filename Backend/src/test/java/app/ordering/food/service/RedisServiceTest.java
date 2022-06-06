package app.ordering.food.service;

import app.ordering.food.common.Result;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class RedisServiceTest {

    @Resource
    private RedisService redisService;

    @Test
    void getKeysTest() {
        redisService.update("key1", "");
        redisService.update("key2", "");
        redisService.update("key3", "");
        redisService.update("key4", "");
        Result<List<String>> result = redisService.getKeys();
        assertEquals(result.getCode(), "0000000");
        result.getData().forEach(System.out::println);
    }

    @Test
    void updateTest() {
        Result<Void> result = redisService.update("key1", "");
        assertEquals(result.getCode(), "0000000");
        Result<String> result1 = redisService.get("key2");
        assertNotEquals(result1.getCode(), "0000000");
    }

    @Test
    void updateTest2() {
        Result<Void> result = redisService.updateWithTtl("key3", "", 1000);
        assertEquals(result.getCode(), "0000000");
    }

    @Test
    void deleteTest1() {
        Result<Void> result = redisService.delete("testtesttesttest");
        assertEquals(result.getCode(), "004B002");
    }

    @Test
    void deleteTest2() {
        Result<Void> result = redisService.update("testtesttesttestX", "");
        assertEquals(result.getCode(), "0000000");
        Result<Void> result1 = redisService.delete("testtesttesttestX");
        assertEquals(result1.getCode(), "0000000");
    }

    @AfterEach
    public void flushDb() {
        redisService.flushDb();
    }
}
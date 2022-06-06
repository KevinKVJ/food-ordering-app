package app.ordering.food.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MinioServiceTest {

    @Resource
    private MinioService minioService;

    @Test
    void removeBucket() {
        String bucket = "food-ordering-app";
        minioService.removeBucket(bucket);
    }
}
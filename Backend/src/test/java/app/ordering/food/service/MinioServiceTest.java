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

    @Test
    void getImage64() {
        String bucket = "food-ordering-app-products";
        String filename = "1.jpg";
        System.out.println(minioService.downloadBase64(bucket, filename));
    }
}
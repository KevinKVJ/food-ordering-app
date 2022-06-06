package app.ordering.food.service;

import app.ordering.food.common.Result;
import app.ordering.food.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
class ProductServiceTest {

    @Resource
    private ProductService productService;

    @Resource
    private MinioService minioService;

    @Value("${minio.bucketForProducts}")
    private String bucket;

    @Test
    void list() {
        List<Product> productList = productService.list();
        productList.forEach(System.out::println);
    }
    @Test
    void create() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "productTest");
        requestBody.put("monthly", 100);
        requestBody.put("inventory", 105);
        requestBody.put("discount", 0);
        requestBody.put("price", 1250);
        Result<Product> result = productService.insert(requestBody);
        Assertions.assertEquals(result.getCode(), "0000000");
        String productImage = result.getData().getId() + ".jpg";
        Assertions.assertTrue(minioService.existObject(bucket, productImage));
        System.out.println(result.getData());
        minioService.removeBucket(bucket);
    }
}
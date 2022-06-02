package app.ordering.food.service;

import app.ordering.food.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Resource
    private ProductService productService;
    @Test
    void list() {
        List<Product> productList = productService.list();
        productList.forEach(System.out::println);
    }
}
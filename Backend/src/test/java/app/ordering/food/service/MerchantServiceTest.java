package app.ordering.food.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MerchantServiceTest {
    @Resource
    private MerchantService merchantService;
    @Test
    void list() {
        System.out.println(merchantService.getMerchants());
    }
}
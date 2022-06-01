package app.ordering.food.service;

import app.ordering.food.entity.Merchant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MerchantServiceTest {
    @Resource
    private MerchantService merchantService;
    @Test
    void list() {
        List<Merchant> merchants = merchantService.list();
        merchants.forEach(System.out::println);
    }
}
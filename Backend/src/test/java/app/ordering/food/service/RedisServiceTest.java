package app.ordering.food.service;

import app.ordering.food.entity.Client;
import app.ordering.food.entity.Merchant;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
class RedisServiceTest {

    @Resource
    private RedisService redisService;

    @Resource
    private MerchantService merchantService;

    @Resource
    private ClientService clientService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void getKeys() {
        redisService.updateString("key1", "val1");
        redisService.updateString("key2", "val2");
        redisService.updateString("key3", "val3");
        redisService.updateString("key4", "val4");
        List<String> result = redisService.getKeys();
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    void insertMerchant() {
        Merchant merchant = new Merchant();
        merchant.setPhone("9998887777");
        merchant.setPassword(bCryptPasswordEncoder.encode("passwordTest"));
        assertTrue(merchantService.save(merchant));
        redisService.updateObject("a", merchant);
        Merchant merchant2 = redisService.getObject("a");
        assertNotNull(merchant2);
    }

    @Test
    void insertClient() {
        Client client = new Client();
        client.setPhone("7778889999");
        client.setPassword(bCryptPasswordEncoder.encode("passwordTest2"));
        assertTrue(clientService.save(client));
        redisService.updateObject("b", client);
        Client client2 = redisService.getObject("b");
        assertNotNull(client2);
    }

    @Test
    void updateTest() {
        assertTrue(redisService.updateString("key1", ""));
        assertNull(redisService.getString("key2"));
    }

    @Test
    void updateTest2() {
        assertTrue(redisService.updateStringWithTtl("key3", "", 1000));
    }

    @Test
    void deleteTest1() {
        assertFalse(redisService.delete("testtesttesttest"));
    }

    @Test
    void deleteTest2() {
        assertTrue(redisService.updateString("testtesttesttestX", ""));
        assertTrue(redisService.delete("testtesttesttestX"));
    }

    @AfterEach
    public void flushDb() {
        redisService.flushDb();
    }
}
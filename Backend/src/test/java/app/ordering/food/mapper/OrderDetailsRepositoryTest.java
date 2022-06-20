package app.ordering.food.mapper;

import app.ordering.food.entity.OrderDetails;
import app.ordering.food.repository.OrderDetailsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class OrderDetailsRepositoryTest {
    @Resource
    private OrderDetailsRepository orderDetailsRepository;

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    void listOrderDetails() {
        orderDetailsRepository.insert(
                new OrderDetails(1, new ArrayList<Map<String, Object>>() {{
                    add(new HashMap<String, Object>() {{
                        put("name", "name1");
                        put("options", null);
                        put("price", 100);
                        put("count", 105);
                    }});
                }})
        );
        orderDetailsRepository.insert(
                new OrderDetails(2, new ArrayList<Map<String, Object>>() {{
                    add(new HashMap<String, Object>() {{
                        put("name", "name2");
                        put("options", "options2");
                        put("price", 1000);
                        put("count", 1050);
                    }});
                    add(new HashMap<String, Object>() {{
                        put("name", "name3");
                        put("options", "options3");
                        put("price", 1000);
                        put("count", 1050);
                    }});
                    add(new HashMap<String, Object>() {{
                        put("name", "name4");
                        put("options", "options4");
                        put("price", 500);
                        put("count", 1050);
                    }});
                }})
        );
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        orderDetails.forEach(System.out::println);
        assertNull(orderDetailsRepository.findByOrderId(-1));
        OrderDetails orderDetails1 = orderDetailsRepository.findByOrderId(2);
        assertNotNull(orderDetails1);
        orderDetails1.getProducts().forEach(System.out::println);
    }

    @Test
    void updateOrderDetails() {
        orderDetailsRepository.insert(
                new OrderDetails(3, new ArrayList<Map<String, Object>>() {{
                    add(new HashMap<String, Object>() {{
                        put("name", "name1");
                        put("options", null);
                        put("price", 100);
                        put("count", 105);
                    }});
                }})

        );
        OrderDetails orderDetails1 = orderDetailsRepository.findByOrderId(3);
        assertNotNull(orderDetails1);
        System.out.println(orderDetails1);
        orderDetails1.getProducts()
                .add(new HashMap<String, Object>() {{
                    put("name", "name9");
                    put("options", "xxx");
                    put("price", 10000);
                    put("count", 10500);
                }});
        OrderDetails newOrderDetails1 = orderDetailsRepository.save(orderDetails1);
        assertNotNull(newOrderDetails1);
        System.out.println(newOrderDetails1);
    }


    @AfterEach
    void flushDb() {
        mongoTemplate.getDb().drop();
    }
}
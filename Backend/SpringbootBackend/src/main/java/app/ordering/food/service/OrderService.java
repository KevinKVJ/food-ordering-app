package app.ordering.food.service;

import app.ordering.food.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Order> {
    List<Map<String, Object>> getOrders();
    List<Map<String, Object>> getOrdersOfMerchant(String id);
}

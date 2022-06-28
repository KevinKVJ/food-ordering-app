package app.ordering.food.service.impl;

import app.ordering.food.entity.Order;
import app.ordering.food.mapper.OrderMapper;
import app.ordering.food.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Override
    public List<Map<String, Object>> getOrders() {
        return baseMapper.getOrders();
    }

    @Override
    public List<Map<String, Object>> getOrdersOfMerchant(String id) {
        return baseMapper.getOrdersOfMerchant(id);
    }
}

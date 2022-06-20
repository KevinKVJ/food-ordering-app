package app.ordering.food.service.impl;

import app.ordering.food.entity.Order;
import app.ordering.food.mapper.OrderMapper;
import app.ordering.food.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}

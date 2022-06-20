package app.ordering.food.service.impl;

import app.ordering.food.entity.OrderStatus;
import app.ordering.food.mapper.OrderStatusMapper;
import app.ordering.food.service.OrderStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("orderStatusService")
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements OrderStatusService {
}

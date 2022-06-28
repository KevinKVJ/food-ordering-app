package app.ordering.food.service.impl;

import app.ordering.food.entity.OrderDeliveryMethod;
import app.ordering.food.mapper.OrderDeliveryMethodMapper;
import app.ordering.food.service.OrderDeliveryMethodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("orderDeliveryMethodService")
public class OrderDeliveryMethodServiceImpl extends ServiceImpl<OrderDeliveryMethodMapper, OrderDeliveryMethod> implements OrderDeliveryMethodService {
}

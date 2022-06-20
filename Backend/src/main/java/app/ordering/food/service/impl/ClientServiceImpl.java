package app.ordering.food.service.impl;

import app.ordering.food.entity.Client;
import app.ordering.food.mapper.ClientMapper;
import app.ordering.food.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("clientService")
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
}

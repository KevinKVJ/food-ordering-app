package app.ordering.food.service.impl;

import app.ordering.food.entity.Merchant;
import app.ordering.food.mapper.MerchantMapper;
import app.ordering.food.service.MerchantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

}
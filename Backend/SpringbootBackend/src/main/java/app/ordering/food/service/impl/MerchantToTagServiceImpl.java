package app.ordering.food.service.impl;

import app.ordering.food.entity.MerchantToTag;
import app.ordering.food.mapper.MerchantToTagMapper;
import app.ordering.food.service.MerchantToTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("merchantToTagService")
public class MerchantToTagServiceImpl extends ServiceImpl<MerchantToTagMapper, MerchantToTag> implements MerchantToTagService {
}

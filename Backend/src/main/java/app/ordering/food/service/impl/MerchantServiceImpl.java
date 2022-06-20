package app.ordering.food.service.impl;

import app.ordering.food.entity.Merchant;
import app.ordering.food.mapper.MerchantMapper;
import app.ordering.food.service.MerchantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    @Override
    // @Cacheable(value = "merchantServiceCache", key = "'list'")
    public List<Merchant> list() {
        return super.list();
    }

    // 保证缓存的时候该id存在
    @Override
    // @Cacheable(value = "merchantServiceCache", key = "#id", unless = "#result==null")
    public Merchant getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @CacheEvict(value = "merchantServiceCache", key = "'list'")
    public boolean save(Merchant merchant) {
        return super.save(merchant);
    }

    // 保证缓存的时候该phone存在
    @Override
    // @Cacheable(value = "merchantServiceCache", key = "#phone", unless = "#result==null")
    public Merchant getByPhone(String phone) {
        return phone == null ? null : super.getOne(new QueryWrapper<Merchant>()
                .eq("phone", phone));
    }

    @Override
    // @Caching(evict = {@CacheEvict(value = "merchantServiceCache", key = "'list'")}, put = {@CachePut(value =
    //       "merchantServiceCache", key = "#merchant.id"), @CachePut(value = "merchantServiceCache", key =
    //       "#merchant.phone")})
    public boolean updateById(Merchant merchant) {
        return super.updateById(merchant);
    }


}
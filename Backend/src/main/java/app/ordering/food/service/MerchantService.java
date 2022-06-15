package app.ordering.food.service;

import app.ordering.food.entity.Merchant;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MerchantService extends IService<Merchant> {
    Merchant getByPhone(String phone);
}

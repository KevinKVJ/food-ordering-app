package app.ordering.food.service;

import app.ordering.food.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<Tag> getTags(String merchantId);
}

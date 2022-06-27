package app.ordering.food.service.impl;

import app.ordering.food.entity.Tag;
import app.ordering.food.mapper.TagMapper;
import app.ordering.food.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    public List<Tag> getTags(String merchantId) {
        return baseMapper.getTags(merchantId);
    }
}

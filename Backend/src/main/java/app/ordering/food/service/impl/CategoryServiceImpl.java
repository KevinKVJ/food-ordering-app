package app.ordering.food.service.impl;

import app.ordering.food.entity.Category;
import app.ordering.food.entity.Product;
import app.ordering.food.mapper.CategoryMapper;
import app.ordering.food.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> list() {
        return super.list();
    }

    @Override
    public List<Category> listByMerchantId(Integer merchantId) {
        if (merchantId == null) {
            return null;
        }
        return this.list(new QueryWrapper<Category>().eq("merchant_id", merchantId));
    }

    @Override
    public Category getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public List<Category> getCategoriesByProductId(Integer id) {
        return baseMapper.getCategoriesByProductId(id);
    }

    @Override
    public List<Product> getProductsByCategoryId(Integer id) {
        return baseMapper.getProductsByCategoryId(id);
    }
}

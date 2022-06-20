package app.ordering.food.service.impl;

import app.ordering.food.entity.ProductToCategory;
import app.ordering.food.mapper.ProductToCategoryMapper;
import app.ordering.food.service.ProductToCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("productToCategoryService")
public class ProductToCategoryServiceImpl extends ServiceImpl<ProductToCategoryMapper, ProductToCategory> implements ProductToCategoryService {
}

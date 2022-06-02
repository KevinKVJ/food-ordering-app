package app.ordering.food.service.impl;

import app.ordering.food.entity.Product;
import app.ordering.food.mapper.ProductMapper;
import app.ordering.food.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}

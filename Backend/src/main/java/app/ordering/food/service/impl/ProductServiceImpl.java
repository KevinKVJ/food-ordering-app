package app.ordering.food.service.impl;

import app.ordering.food.entity.Product;
import app.ordering.food.mapper.ProductMapper;
import app.ordering.food.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    public List<Product> list() {
        return super.list();
    }

    @Override
    public Product getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Product product) {
        return super.save(product);
    }

    @Override
    public boolean updateById(Product product) {
        return super.updateById(product);
    }
}

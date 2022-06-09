package app.ordering.food.service;

import app.ordering.food.entity.Category;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> listByMerchantId(Integer merchantId);
    List<Category> getCategoriesByProductId(Integer id);
    List<Product> getProductsByCategoryId(Integer id);
}

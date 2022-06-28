package app.ordering.food.service;

import app.ordering.food.entity.Category;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> listByMerchantId(String merchantId);
    List<Category> getCategoriesByProductId(String id);
    List<Product> getProductsByCategoryId(String id);
}

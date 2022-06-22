package app.ordering.food.mapper;

import app.ordering.food.entity.Category;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("SELECT *\n" +
            "FROM t_category\n" +
            "WHERE t_category.id IN\n" +
            "      (SELECT t_product_to_category.category_id\n" +
            "       FROM t_product_to_category\n" +
            "       WHERE t_product_to_category.product_id = #{productId});")
    List<Category> getCategoriesByProductId(String productId);

    @Select("SELECT *\n" +
            "FROM t_product\n" +
            "WHERE t_product.id IN\n" +
            "      (SELECT t_product_to_category.product_id\n" +
            "       FROM t_product_to_category\n" +
            "       WHERE t_product_to_category.category_id = #{categoryId});")
    List<Product> getProductsByCategoryId(String categoryId);
}

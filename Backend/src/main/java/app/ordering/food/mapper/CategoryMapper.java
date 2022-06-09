package app.ordering.food.mapper;

import app.ordering.food.entity.Category;
import app.ordering.food.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    @Select("select *\n" +
            "from t_category\n" +
            "where t_category.id in\n" +
            "      (select t_product_to_category.category_id\n" +
            "       from t_product_to_category\n" +
            "       where t_product_to_category.product_id = #{productId});")
    List<Category> getCategoriesByProductId(Integer productId);

    @Select("select *\n" +
            "from t_product\n" +
            "where t_product.id in\n" +
            "      (select t_product_to_category.product_id\n" +
            "       from t_product_to_category\n" +
            "       where t_product_to_category.category_id = #{categoryId});")
    List<Product> getProductsByCategoryId(Integer categoryId);
}

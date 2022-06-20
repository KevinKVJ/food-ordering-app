package app.ordering.food.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product_to_category")
public class ProductToCategory implements Serializable {
    private static final long serialVersionUID = 3567653491060394674L;
    private Integer productId;
    private Integer categoryId;
}

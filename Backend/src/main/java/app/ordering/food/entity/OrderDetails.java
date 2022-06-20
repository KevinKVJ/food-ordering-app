package app.ordering.food.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "t_order_details")
public class OrderDetails {
    @Id
    private String id;
    private Integer orderId;
    private List<Map<String, Object>> products;

    public OrderDetails(Integer orderId, List<Map<String, Object>> products) {
        this.orderId = orderId;
        this.products = products;
    }
}

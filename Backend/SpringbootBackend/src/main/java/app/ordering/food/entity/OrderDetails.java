package app.ordering.food.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "t_order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 3567653491060394679L;

    @Id
    private String id;
    private String orderId;
    private List<Map<String, Object>> products;

    public OrderDetails(String orderId, List<Map<String, Object>> products) {
        this.orderId = orderId;
        this.products = products;
    }
}

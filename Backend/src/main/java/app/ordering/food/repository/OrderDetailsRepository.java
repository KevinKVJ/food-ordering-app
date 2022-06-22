package app.ordering.food.repository;

import app.ordering.food.entity.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
    OrderDetails findByOrderId(String orderId);
}

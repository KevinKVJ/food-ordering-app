package app.ordering.food.mapper;

import app.ordering.food.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select t_order.*,\n" +
            "       t_order_status.description          as status_description,\n" +
            "       t_order_delivery_method.description as delivery_method,\n" +
            "       t_client.name                       as client_name\n" +
            "from t_order,\n" +
            "     t_order_delivery_method,\n" +
            "     t_order_status,\n" +
            "     t_client\n" +
            "where t_order.client_id = t_client.id\n" +
            "  and t_order.delivery_method_id = t_order_delivery_method.id\n" +
            "  and t_order.status_id = t_order_status.id;")
    List<Map<String, Object>> getOrders();

    @Select("select t_order.*,\n" +
            "       t_order_status.description          as status_description,\n" +
            "       t_order_delivery_method.description as delivery_method,\n" +
            "       t_client.name                       as client_name\n" +
            "from t_order,\n" +
            "     t_order_delivery_method,\n" +
            "     t_order_status,\n" +
            "     t_client\n" +
            "where t_order.client_id = t_client.id\n" +
            "  and t_order.delivery_method_id = t_order_delivery_method.id\n" +
            "  and t_order.status_id = t_order_status.id\n" +
            "  and t_order.merchant_id = #{merchantId};")
    List<Map<String, Object>> getOrdersOfMerchant(String merchantId);
}

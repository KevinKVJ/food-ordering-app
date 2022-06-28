package app.ordering.food.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_merchant_to_tag")
public class MerchantToTag implements Serializable {
    private static final long   serialVersionUID = 3567653491060394669L;
    private              String merchantId;
    private              String tagId;
}

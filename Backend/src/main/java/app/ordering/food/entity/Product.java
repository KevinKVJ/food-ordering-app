package app.ordering.food.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_product")
public class Product implements Serializable {
    private static final long          serialVersionUID = 3567653491060394672L;
    @TableId(type = IdType.AUTO)
    private              Integer       id;
    private              String        name;
    private              Integer       monthly;
    private              Integer       inventory;
    private              Integer       discount;
    private              Integer       price;
    private              Integer       merchantId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private              LocalDateTime createAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private              LocalDateTime updateAt;
}

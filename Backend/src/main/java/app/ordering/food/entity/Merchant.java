package app.ordering.food.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_merchant")
public class Merchant implements Serializable {
    private static final long serialVersionUID = 3567653491060394671L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String phone;
    private String password;
    private String name;
    private String description;
    private String address;
    private String publicPhone;
    private String publicAddress;
    private String businessHours;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;
}

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
@TableName("t_category")
public class Category implements Serializable {
    private static final long          serialVersionUID = 3567653491060394673L;
    @TableId(type = IdType.AUTO)
    private              Integer       id;
    private              String        description;
    private              Integer       merchantId;
    @TableField(fill = FieldFill.INSERT)
    private              LocalDateTime createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private              LocalDateTime updateAt;
}

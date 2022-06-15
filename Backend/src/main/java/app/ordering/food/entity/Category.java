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
@TableName("t_category")
public class Category implements Serializable {
    private static final long          serialVersionUID = 3567653491060394673L;
    @TableId(type = IdType.AUTO)
    private              Integer id;
    private              String  name;
    private              Integer merchantId;
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private              LocalDateTime createAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private              LocalDateTime updateAt;
}

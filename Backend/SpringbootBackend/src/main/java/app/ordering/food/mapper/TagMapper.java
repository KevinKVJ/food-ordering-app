package app.ordering.food.mapper;

import app.ordering.food.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select *\n" +
            "from t_tag\n" +
            "where t_tag.id in\n" +
            "      (select t_merchant_to_tag.tag_id\n" +
            "       from t_merchant_to_tag\n" +
            "       where t_merchant_to_tag.merchant_id = #{merchantId});")
    List<Tag> getTags(String merchantId);
}

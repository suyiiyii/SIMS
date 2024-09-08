package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.sims.entity.HierarchyRelation;
import top.suyiiyii.sims.entity.User;

import java.util.List;

public interface HierarchyMapper extends BaseMapper<HierarchyRelation> {

    @Select("SELECT * FROM user WHERE id = " +
            "(SELECT hierarchy_relation.superior_user_id FROM hierarchy_relation WHERE subordinate_user_id = #{userId})")
    User getSuperiorUser(int userId);

    @Select("SELECT * FROM user WHERE id = " +
            "(SELECT hierarchy_relation.subordinate_user_id FROM hierarchy_relation WHERE superior_user_id = #{userId})")
    List<User> getSubordinateUser(int userId);
}

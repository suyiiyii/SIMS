package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.sims.entity.Permissions;
import top.suyiiyii.sims.entity.RolePermission;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/14 16:14
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: PermissionsMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface PermissionsMapper  {
@Select("SELECT * FROM role_permission WHERE role_id = #{id}")
    List<RolePermission> getRolePerminsionByRoleId(Integer id);
@Select("SELECT * FROM permissions WHERE permission_id = #{permissionId}")
    Permissions selectById(Integer permissionId);
}

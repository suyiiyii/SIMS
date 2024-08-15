package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import top.suyiiyii.sims.entity.Permissions;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.entity.UserRole;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/14 14:13
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: RoleMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface RoleMapper {
    @Insert("INSERT INTO role(name) VALUES(#{name}")
    void addRole(String name);
@Delete("DELETE FROM role WHERE name=#{name}")
    void deleteRole(String name);
@Update("UPDATE role SET name=#{newName} WHERE name=#{name}")
    void updateRole(String name, String newName);
    /**
     * @author: tortoise
     * @date: 2024/8/14 14:23
     * @Description: TODO 查询用户信息
     * @param
     * @return: java.util.List<top.suyiiyii.sims.entity.User>
     */
    @Select("SELECT u.username, u.name, u.userId, r.role_name " +
            "FROM user u " +
            "LEFT JOIN user_role ur ON u.user_id = ur.user_id " +
            "LEFT JOIN role r ON ur.role_id = r.role_id")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "group", column = "group"),
            @Result(property = "roles", column = "role_name", many = @Many(select = "selectRolesByUser"))
    })
    List<User> selectAllUsersWithRoles();

    // 根据用户ID查询角色
    @Select("SELECT role_id, role_name " +
            "FROM role " +
            "WHERE role_id IN " +
            "(SELECT role_id FROM user_role WHERE user_id = #{user_id})")
    List<UserRole> selectRolesById(@Param("user_id") int id);

@Select("SELECT role_name FROM role WHERE role_id=#{roleId}")
    List<String> selectRoleNamesByRoleId(Integer roleId);
}

package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.*;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;

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

    /**
     * @param
     * @author: tortoise
     * @date: 2024/8/14 14:23
     * @Description: TODO 查询用户信息
     * @return: java.util.List<top.suyiiyii.sims.entity.User>
     */
    @Select("SELECT u.username, u.student_id, r.role_name " +
            "FROM user u " +
            "LEFT JOIN user_role ur ON u.student_id = ur.user_id " +
            "LEFT JOIN role r ON ur.role_id = r.id")
    @Results({
            @Result(property = "username", column = "username"),

            @Result(property = "userId", column = "userId"),
            @Result(property = "group", column = "group"),
            @Result(property = "roles", column = "role_name", many = @Many(select = "selectRolesById"))
    })
    List<User> selectAllUsersWithRoles();

    // 根据用户ID查询角色
    @Select("SELECT role_id, role_name " +
            "FROM role " +
            "WHERE id IN " +
            "(SELECT role_id FROM user_role WHERE user_id = #{student_id})")
    List<Role> selectRolesById(@Param("student_id") int id);

    @Select("SELECT role_name FROM role WHERE role_id=#{roleId}")
    List<String> selectRoleNamesByRoleId(Integer roleId);

    @Select("SELECT user_id " +
            "FROM user_role " +
            "WHERE role_id IN " +
        "(SELECT role_id FROM role WHERE role_name=#{roleName})")
    Integer getIdByrolename(String roleName);
@Select("SELECT student_id FROM user WHERE username=#{username}")
    Integer getStudentIdByUsername(String username);

    @Select("SELECT role_name " +
            "FROM role " +
            "WHERE id IN " +
            "(SELECT role_id FROM user_role WHERE user_id = #{id})")
    List<String> getRolesById(int id);
}

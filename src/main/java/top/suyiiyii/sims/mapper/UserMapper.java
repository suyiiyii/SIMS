package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/10 22:21
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: UserMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 添加新用户
     * @param user 新用户对象
     * @return 影响的行数
     */
    @Insert("insert INTO user (id,user_id, username, password, name, email, `group`) VALUES (#{id},#{userId}, #{username}, #{password}, #{name}, #{email}, #{group})")
    int addUser(User user);

    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 影响的行数
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(Integer id);

    /**
     * 更新用户信息
     * @param user 更新后的用户对象
     * @return 影响的行数
     */
    @Update("UPDATE user SET " +
            "user_id = #{userId}, " +
            "username = #{username}, " +
            "name = #{name}, " +
            "email = #{email}, " +
            "`group` = #{group} " +
            "WHERE id = #{id}")
    int updateUser(User user);

    /**
     * 根据ID查询用户信息
     * @param
     * @return 用户对象
     */
    @Select("SELECT id, user_id, username, password, name, email, `group` from user WHERE id = #{Id}")
    User selectByUserId(Integer userId);

    /**
     * 根据iD查询用户信息
     * @param
     * @return 用户对象
     */
    @Select("SELECT id, user_id, username, password, name, email, `group` from user WHERE id = #{Id}")
    User selectById(Integer Id);
    /**
     * 查询所有用户信息
     * @return 用户列表
     */
    @Select("SELECT id, user_id, username, password, name, email,grade, `group` FROM user")
    List<User> selectAll();

  @Select("select * from user where username = #{username}")
   User selectByUserName(@Param("username") String username);
@Update("update user set password = #{password} where username = #{username}")
    void updatePassword(User user);

}

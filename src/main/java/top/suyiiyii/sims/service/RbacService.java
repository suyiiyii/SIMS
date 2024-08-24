package top.suyiiyii.sims.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.UserRole;
import top.suyiiyii.sims.mapper.MpRoleMapper;
import top.suyiiyii.sims.mapper.MpUserMapper;
import top.suyiiyii.sims.mapper.MpUserRoleMapper;

import java.util.List;

@Slf4j
@Service
public class RbacService {

    @Autowired
    MpUserMapper userMapper;

    @Autowired
    MpUserRoleMapper userRoleMapper;

    @Autowired
    MpRoleMapper roleMapper;

    /**
     * 根据用户id获取用户的角色
     *
     * @param userId 用户id
     * @return 用户的角色列表
     */
    public List<Role> getRolesByUserId(int userId) {
        // 根据用户id获取用户的角色id，使用mp的条件构造器
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
        // 根据角色id获取角色
        return roleMapper.selectBatchIds(userRoles.stream().map(UserRole::getRoleId).toList());
    }

    public boolean addRoleWithUserId(int userId, String roleName) {
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", roleName));
        if (role == null) {
            Role newRole = new Role();
            newRole.setRoleName(roleName);
            roleMapper.insert(newRole);
            role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", roleName));
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(role.getId());
        return userRoleMapper.insert(userRole) > 0;
    }

}

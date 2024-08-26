package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.mapper.RoleMapper;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/14 14:14
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: RoleService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<User> findAllUsersWithRoles() {
        return roleMapper.selectAllUsersWithRoles();
    }

    /**
     * @param
     * @author: tortoise
     * @date: 2024/8/14 14:39
     * @Description: TODO 查看自己身份
     * @return: java.util.List<top.suyiiyii.sims.entity.Role>
     */
    public List<Role> selectRolesById(int id) {
        return roleMapper.selectRolesById(id);
    }


    public boolean isRoleNameAdmin(Integer id) {
        List<Role> roles = roleMapper.selectRolesById(id);
        for (Role role : roles) {
            if (role.getRoleName().equals("admin")) {
                return true;
            }
        }
        return false;
    }

    public Integer getIdByrolename(String roleName) {
        return roleMapper.getIdByrolename(roleName);
    }

    public Integer getStudentIdByUsername(String username) {
        return roleMapper.getStudentIdByUsername(username);
    }
}

package top.suyiiyii.sims.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.entity.UserRole;
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
    public void addRole(String name){
        roleMapper.addRole(name);
    }
    public void deleteRole(String name){
        roleMapper.deleteRole(name);
    }
    public void updateRole(String name,String newName){
        roleMapper.updateRole(name,newName);
    }
    public List<User> findAllUsersWithRoles(){
        return roleMapper.selectAllUsersWithRoles();
    }
    /**
     * @author: tortoise
     * @date: 2024/8/14 14:39
     * @Description: TODO 查看自己身份
     * @param Id
     * @return: java.util.List<top.suyiiyii.sims.entity.Role>
     */
    List<UserRole> selectRolesById(int id){
        return roleMapper.selectRolesById(id);
    }


}

package top.suyiiyii.sims.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.suyiiyii.sims.VO.UserVO;
import top.suyiiyii.sims.entity.*;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.PermissionsMapper;
import top.suyiiyii.sims.mapper.RoleMapper;
import top.suyiiyii.sims.mapper.UserMapper;
import top.suyiiyii.sims.utils.JwtUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/10 22:22
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionsMapper permissionsMapper;

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
//TODO:返回一个DTO,用户基本信息
    public User login(String username, String password)  {
        User dbUser = userMapper.selectByUserName(username);
        if (dbUser == null) {
            throw new ServiceException("账号不存在");
        }
        if (!dbUser.getPassword().equals(password)) {
            throw new ServiceException("密码或用户名错误");
        }
        HashSet<Permissions> permissionsSet = new HashSet<>();
        Integer id = dbUser.getId();
        List<UserRole> UserRoles = roleMapper.selectRolesById(id);
        for (UserRole userRole : UserRoles) {
            //根据roleid找所有permissionId
            List<RolePermission> rolePerminsion = permissionsMapper.getRolePerminsionByRoleId(userRole.getRoleId());
            for (RolePermission rolePermission : rolePerminsion) {
                Integer permissionId = rolePermission.getPermissionId();
                //根据permissionId找permission
                Permissions permissions = permissionsMapper.selectById(permissionId);
                permissionsSet.add(permissions);
            }
        }
        dbUser.setPermissions(permissionsSet);

        String token = JwtUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User register(User user) {

        User dbUser = userMapper.selectByUserId(user.getStudentId());

        if (user.getUsername() == null || user.getUsername().equals("")) {
        throw new ServiceException("用户名不能为空");
    }
        if (dbUser != null) {
            throw new ServiceException("账号已经存在");
        }
        if (user.getStudentId() == null || user.getStudentId().equals("")) {
            throw new ServiceException("用户id不能为空");
        }
        if( user.getPassword() == null || user.getPassword().equals("")) {
            throw new ServiceException("密码不能为空");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new ServiceException("邮箱不能为空");
        }
        if (user.getGroup() == null || user.getGroup().equals("")) {
            throw new ServiceException("组别不能为空");
        }

            userMapper.addUser(user);
            return user;
    }
    public User selectByUsername(String username) {
        return userMapper.selectByUserName(username);
    }
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
    public List<UserVO> findAllUsers(){
        List<User> users = userMapper.selectAll();
        List<UserVO> userVOS = new ArrayList<>();

        for (User user : users) {
            UserVO userVO = new UserVO();
            userVO.setUserId(user.getId());
            userVO.setUsername(user.getUsername());
            userVO.setGrade(user.getGrade());
            userVO.setGroup(user.getGroup());
            userVO.setRoles(new ArrayList<>());
            Integer id = user.getId();
            List<UserRole> userRoles = roleMapper.selectRolesById(id);
            for (UserRole userRole : userRoles) {
                Integer roleId = userRole.getRoleId();
                // 获取一个角色的名称列表
                List<String> roleNameList = roleMapper.selectRoleNamesByRoleId(roleId);
                // 累加角色名称到用户的角色列表中
                userVO.getRoles().addAll(roleNameList);
            }
            userVOS.add(userVO);
        }
        return userVOS;
    }

}

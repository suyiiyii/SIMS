package top.suyiiyii.sims.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.*;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.*;
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
    MpUserMapper mpUserMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionsMapper permissionsMapper;
    @Value("${jwt.secret}")
    private String secret;

    public void addUser(User user) {
        userMapper.addUser(user);
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
    public String login(String username, String password)  {

        User dbUser = userMapper.selectByUserName(username);
        if (dbUser == null) {
            throw new ServiceException("账号不存在");
        }
        if (!dbUser.getPassword().equals(password)) {
            throw new ServiceException("密码或用户名错误");
        }
        HashSet<Permissions> permissionsSet = new HashSet<>();
        Integer id = dbUser.getId();
        List<Role> roles = roleMapper.selectRolesById(id);
        for (Role role : roles) {
            //根据roleid找所有permissionId
            List<RolePermission> rolePerminsion = permissionsMapper.getRolePerminsionByRoleId(role.getRoleId());
            for (RolePermission rolePermission : rolePerminsion) {
                Integer permissionId = rolePermission.getPermissionId();
                //根据permissionId找permission
                Permissions permissions = permissionsMapper.selectById(permissionId);
                permissionsSet.add(permissions);
            }
        }

        String token = JwtUtils.createToken(dbUser.getId().toString(), secret);


        return token;

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
            throw new ServiceException("学号不能为空");
        }
        if( user.getPassword() == null || user.getPassword().equals("")) {
            throw new ServiceException("密码不能为空");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new ServiceException("邮箱不能为空");
        }
        if (user.getUserGroup() == null || user.getUserGroup().equals("")) {
            throw new ServiceException("组别不能为空");
        }

        mpUserMapper.insert(user);
            return user;
    }
    public User selectByUsername(String username) {
        return userMapper.selectByUserName(username);
    }
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
    public List<UserDto> findAllUsers(){
        List<User> users = userMapper.selectAll();
        List<UserDto> UserDtos = new ArrayList<>();

        for (User user : users) {
            UserDto UserDto = new UserDto();
            UserDto.setUserId(user.getId());
            UserDto.setUsername(user.getUsername());
            UserDto.setGrade(user.getGrade());
            UserDto.setUserGroup(user.getUserGroup());
            UserDto.setRoles(new ArrayList<>());
            Integer id = user.getId();
            List<Role> roles = roleMapper.selectRolesById(id);
            for (Role role : roles) {
                Integer roleId = role.getRoleId();
                // 获取一个角色的名称列表
                List<String> roleNameList = roleMapper.selectRoleNamesByRoleId(roleId);
                // 累加角色名称到用户的角色列表中
                UserDto.getRoles().addAll(roleNameList);
            }
            UserDtos.add(UserDto);
        }
        return UserDtos;
    }
    public UserDto findUser(Integer id) {

        UserDto UserDto = new UserDto();
        User user = userMapper.selectById(id);
        UserDto.setUserId(user.getId());
        UserDto.setUsername(user.getUsername());
        UserDto.setGrade(user.getGrade());
        UserDto.setUserGroup(user.getUserGroup());
        UserDto.setRoles(new ArrayList<>());
        List<Role> roles = roleMapper.selectRolesById(id);
        for (Role role : roles) {
            Integer roleId = role.getRoleId();
            // 获取一个角色的名称列表
            List<String> roleNameList = roleMapper.selectRoleNamesByRoleId(roleId);
            // 累加角色名称到用户的角色列表中
            UserDto.getRoles().addAll(roleNameList);
        }


        return UserDto;
    }

    public User selectByUserId(Integer studentId) {
        return userMapper.selectByUserId(studentId);
    }

    public List<Role> selectRolesById(Integer studentId) {
        return roleMapper.selectRolesById(studentId);
    }
}

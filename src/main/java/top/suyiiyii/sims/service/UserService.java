package top.suyiiyii.sims.service;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import top.suyiiyii.sims.controller.UserController;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.*;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.*;
import top.suyiiyii.sims.utils.JwtUtils;

import java.util.*;

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
    @Autowired
    private RbacService rbacService;
    @Autowired
    private ModelMapper modelMapper;

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
        return JwtUtils.createToken(dbUser.getId().toString(), secret);
    }


    public void register(UserController.RegisterRequest req) {

        User dbUser = userMapper.selectByUserId(req.getStudentId());

        if (req.getUsername() == null || req.getUsername().equals("")) {
            throw new ServiceException("用户名不能为空");
        }
        if (dbUser != null) {
            throw new ServiceException("账号已经存在");
        }
        if (req.getStudentId() == null || req.getStudentId().equals("")) {
            throw new ServiceException("学号不能为空");
        }
        if (req.getPassword() == null || req.getPassword().equals("")) {
            throw new ServiceException("密码不能为空");
        }
        if (req.getEmail() == null || req.getEmail().equals("")) {
            throw new ServiceException("邮箱不能为空");
        }
        if (req.getUserGroup() == null || req.getUserGroup().equals("")) {
            throw new ServiceException("组别不能为空");
        }
        User user =modelMapper.map(req, User.class);

        mpUserMapper.insert(user);
        user = mpUserMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, req.getUsername()));
        rbacService.addRoleWithUserId(user.getId(), "user");
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
                Integer roleId = role.getId();
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
            Integer roleId = role.getId();
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

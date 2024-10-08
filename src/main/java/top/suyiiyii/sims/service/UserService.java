package top.suyiiyii.sims.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.controller.UserController;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.HierarchyRelation;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.*;
import top.suyiiyii.sims.utils.JwtUtils;

import java.util.ArrayList;
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
    @Autowired
    private RbacService rbacService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HierarchyMapper hierarchyMapper;

    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    //TODO:返回一个DTO,用户基本信息
    public String login(String username, String password) {

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
        if (dbUser != null) {
            throw new ServiceException("账号已经存在");
        }
        User user = modelMapper.map(req, User.class);
        mpUserMapper.insert(user);
        user = mpUserMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, req.getUsername()));
        rbacService.addRoleWithUserId(user.getId(), "user");
        hierarchyMapper.insert(new HierarchyRelation(null,0, user.getId()));
    }


    public List<UserDto> findAllUsers() {
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
            UserDtos.add(UserDto);
        }
        return UserDtos;
    }

    public UserDto findUser(Integer id) {

        UserDto userDto = new UserDto();
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setGrade(user.getGrade());
        userDto.setUserGroup(user.getUserGroup());
        userDto.setStudentId(user.getStudentId());
        userDto.setRoles(new ArrayList<>());
        List<String> roles = roleMapper.getRolesById(id);
        userDto.setRoles(roles);
        return userDto;
    }

    public User selectByUserId(Integer studentId) {
        return userMapper.selectByUserId(studentId);
    }



    public List<Role> selectRolesById(Integer studentId) {
        return roleMapper.selectRolesById(studentId);
    }

    public Integer getStudentIdByUserId(Integer userId) {
        return userMapper.getStudentIdByUserId(userId);
    }
}

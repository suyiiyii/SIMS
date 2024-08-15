package top.suyiiyii.sims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.VO.UserVO;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.UserDTO;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/14 13:57
 * @PackageName:top.suyiiyii.sims.controller
 * @ClassName: AdminController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @GetMapping("/findAllUsersWithRoles")
    public Result findAllUsersWithRoles() {
        List<User> userList = roleService.findAllUsersWithRoles();
        return Result.success(userList);
    }
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> users = userService.selectAll();
        return Result.success(users);
    }
/**
 * @author: tortoise
 * @date: 2024/8/15 16:27
 * @Description: TODO 查看所有成员的信息（姓名，学号，年级，组别，担任角色）
 * @param
 * @return: top.suyiiyii.sims.common.Result
 */
    @GetMapping("/findAllUsers")
    public Result findAllUsers() {
        List<UserVO> userList = userService.findAllUsers();
        return Result.success(userList);
    }
}

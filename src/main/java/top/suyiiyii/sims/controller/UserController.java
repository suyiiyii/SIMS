package top.suyiiyii.sims.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;


/**
 * @Author tortoise
 * @Date 2024/8/10 22:25
 * @PackageName:top.suyiiyii.sims.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @AuthAccess
    @GetMapping("/")
    public Result hello() {

        return Result.success("success");

    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {

        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {

            return Result.error("用户名或密码不能为空");
        }

        User user = userService.login(request.getUsername(), request.getPassword());

        return Result.success(new LoginResponse());
    }

    @PostMapping("/register")
    public Result<Null> register(@RequestBody RegisterRequest request) {
        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {

            return Result.error("用户名或密码不能为空");
        }
        if (request.getPassword() == null || request.getPassword().length() < 3) {
            throw new ServiceException("密码长度不能小于3位");
        }

        userService.register(new User());

        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.addUser(user);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user) {
        userService.deleteUser(user.getId());
        return Result.success("删除成功");
    }

    /**
     * @param user
     * @author: tortoise
     * @date: 2024/8/14 13:34
     * @Description: 更新用户信息, 自己改的(不包括密码)
     * @return: top.suyiiyii.sims.common.Result
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("更新成功");
    }

    @PostMapping("/select")
    public Result select(@RequestBody User user) {
        return Result.success(userService.selectById(user.getId()));
    }

    @PostMapping("/selectByUsername")
    public Result selectByUsername(@RequestBody User user) {
        return Result.success(userService.selectByUsername(user.getUsername()));
    }

    /**
     * @param user
     * @author: tortoise
     * @date: 2024/8/14 13:48
     * @Description: TODO  用户更新密码
     * @return: top.suyiiyii.sims.common.Result
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody User user) {
        if (StrUtil.isBlank(user.getPassword())) {
            return Result.error("密码不能为空");
        }
        User user1 = userService.selectByUsername(user.getUsername());
        //验证原密码是否正确
        if (!user.getPassword().equals(user1.getPassword())) {
            return Result.error("原密码错误");
        }
        userService.updatePassword(user);
        return Result.success("更新成功");
    }

    /**
     * @param user
     * @author: tortoise
     * @date: 2024/8/14 13:48
     * @Description: TODO 管理员修改密码
     * @return: top.suyiiyii.sims.common.Result
     */
    @PostMapping("/updatePasswordByAdmin")
    public Result updatePasswordByAdmin(@RequestBody User user) {
        if (StrUtil.isBlank(user.getPassword())) {
            return Result.error("密码不能为空");
        }
        userService.updatePassword(user);
        return Result.success("更新成功");
    }

    @Data
    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;
        private String grade;
        private String group;
    }

    @Data
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @Data
    public static class LoginResponse {
        public String token;
    }

}
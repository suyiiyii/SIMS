package top.suyiiyii.sims.controller;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;

import java.util.List;


/**
 * @Author tortoise
 * @Date 2024/8/10 22:25
 * @PackageName:top.suyiiyii.sims.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */

@Slf4j
@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    private  SessionRepository sessionRepository;


    @AuthAccess
    @GetMapping("/")
    public Result hello() {

        return Result.success("success");

    }

    @PostMapping("/user/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        log.info("login request:{}", request);

        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {

            return Result.error("用户名或密码不能为空");
        }
        String token = userService.login(request.getUsername(), request.getPassword());
        if (token == null) {
            return Result.error("用户名或密码错误");
        }
        LoginResponse response = new LoginResponse();
            response.setToken(token);
        return Result.success(response);
    }

    @PostMapping("/user/register")
    public Result<CommonResponse> register(@RequestBody RegisterRequest request) {
        log.info("register request:{}", request);
        if (StrUtil.isBlank(request.getUsername()) || StrUtil.isBlank(request.getPassword())) {

            return Result.error("用户名或密码不能为空");
        }
        if (request.getPassword() == null || request.getPassword().length() < 3) {
            throw new ServiceException("密码长度不能小于3位");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGrade(request.getGrade());
        user.setGroup(request.getGroup());
        userService.register(user);

        return Result.success(CommonResponse.factory("注册成功"));
    }

    @DeleteMapping("/admin/user/{id}")
    public Result<CommonResponse> adminDelete(@PathVariable Integer id) {
        log.info("delete request:{}", id);
        userService.deleteUser(id);
        return Result.success(CommonResponse.factory("删除成功"));
    }

    @GetMapping("/admin/user")
    public Result<List<UserDto>> adminGetById() {
        List<UserDto> allUsers = userService.findAllUsers();
        return Result.success(allUsers);
    }
    @GetMapping("/user/{id}")
    public Result<UserDto> GetById(@PathVariable Integer id) {
        log.info("selectById request:{}", id);
        UserDto user = userService.findUser(id);
        return Result.success(user);
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
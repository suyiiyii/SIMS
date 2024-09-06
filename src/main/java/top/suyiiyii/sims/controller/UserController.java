package top.suyiiyii.sims.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.JwtInterceptor;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.MpUserMapper;
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
    MpUserMapper mpUserMapper;
    @Autowired
    RoleService roleService;


    @AuthAccess(allowRoles = {"guest"})
    @PostMapping("/user/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
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

    @AuthAccess(allowRoles = {"guest"})
    @PostMapping("/user/register")
    public Result<CommonResponse> register(@RequestBody @Valid
                                           RegisterRequest request) {
        log.info("register request:{}", request);
        // 检查 username 是否已存在
        if (mpUserMapper.selectOne(new LambdaQueryWrapper<User>(User.class).eq(User::getUsername, request.getUsername())) != null) {
            throw new ServiceException("用户名已存在");
        }
        // 检查 studentId 是否已存在
        if (mpUserMapper.selectOne(new LambdaQueryWrapper<User>(User.class).eq(User::getStudentId, request.getStudentId())) != null) {
            throw new ServiceException("学号已存在");
        }
        // 检查 email 是否已存在
        if (mpUserMapper.selectOne(new LambdaQueryWrapper<User>(User.class).eq(User::getEmail, request.getEmail())) != null) {
            throw new ServiceException("邮箱已存在");
        }
        userService.register(request);

        return Result.success(CommonResponse.factory("注册成功"));
    }

    @Operation(description = "删除单个用户")
    @AuthAccess(allowRoles = {"admin"})
    @DeleteMapping("/admin/user/{id}")
    public Result<CommonResponse> adminDelete(@PathVariable Integer id) {
        log.info("delete request:{}", id);
        userService.deleteUser(id);
        return Result.success(CommonResponse.factory("删除成功"));
    }

    @Operation(description = "获取所有用户信息")
    @AuthAccess(allowRoles = {"admin"})
    @GetMapping("/admin/user")
    public Result<List<UserDto>> adminGet() {
        List<UserDto> allUsers = userService.findAllUsers();
        return Result.success(allUsers);
    }

    @Operation(description = "根据 id 获取用户信息")
    @AuthAccess(allowRoles = {"admin"})
    @GetMapping("/admin/user/{id}")
    public Result<UserDto> adminGetById(@PathVariable Integer id) {
        log.info("selectById request:{}", id);
        UserDto user = userService.findUser(id);
        return Result.success(user);
    }

    @Operation(description = "获取当前用户信息")
    @AuthAccess(allowRoles = {"user"})
    @GetMapping("/user/me")
    public Result<UserDto> getSelf(HttpServletRequest request) {
        int userId = JwtInterceptor.getUserIdFromReq(request);
        UserDto user = userService.findUser(userId);
        return Result.success(user);
    }


    @Data
    public static class RegisterRequest {
        @Length(min = 3, max = 20)
        private String username;
        @Length(min = 6, max = 20)
        private String password;
        @Range(min = 1, max = 1000000000)
        private Integer studentId;
        @Email
        private String email;
        @Length(min = 1, max = 20)
        private String grade;
        @Length(min = 1, max = 20)
        private String userGroup;
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
package top.suyiiyii.sims.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
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
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
@AuthAccess
    @GetMapping("/")
    public Result hello(){

        return Result.success("success");

    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())||StrUtil.isBlank(user.getPassword())){

            return Result.error("用户名或密码不能为空");
        }
        user =userService.login(user);


        return Result.success(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())||StrUtil.isBlank(user.getPassword())){

            return Result.error("用户名或密码不能为空");
        }
        if(user.getPassword() == null || user.getPassword().length() < 3) {
            throw new ServiceException("密码长度不能小于3位");
        }

        user =userService.register(user);

        return Result.success(user);
    }



    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> users = userService.selectAll();
        return Result.success(users);
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
}

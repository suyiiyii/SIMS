package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.service.HierarchyService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hierarchy")
public class HierarchyController {

    @Autowired
    private HierarchyService hierarchyService;

    @GetMapping("/{userId}/SuperiorUser")
    @Operation(summary = "获取用户的上级（管理员）")
    @AuthAccess(allowRoles = {"admin"})
    public UserDto getSuperiorUser(@PathVariable int userId) {
        User user = hierarchyService.getSuperiorUser(userId);
        if (user == null) {
            throw new ServiceException("该用户没有上级");
        }
        return new UserDto(user);
    }

    @GetMapping("/{userId}/subordinateUser")
    @Operation(summary = "获取用户的下级（管理员）")
    @AuthAccess(allowRoles = {"admin"})
    public List<UserDto> getSubordinateUser(@PathVariable int userId) {
        List<User> users = hierarchyService.getSubordinateUser(userId);
        if (users == null || users.isEmpty()) {
            throw new ServiceException("该用户没有下级");
        }
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{userId}/SuperiorUser")
    @Operation(summary = "设置用户的上级（管理员）")
    @AuthAccess(allowRoles = {"admin"})

    public Result setSuperiorUser(@PathVariable int userId, @RequestParam int superiorUserId) {
        hierarchyService.setSuperiorUser(userId, superiorUserId);
        return Result.success();
    }

    @DeleteMapping("/{userId}/SuperiorUser")
    @Operation(summary = "删除用户的上级（管理员）")
    @AuthAccess(allowRoles = {"admin"})
    public Result deleteSuperiorUser(@PathVariable int userId) {
        hierarchyService.setSuperiorUser(userId, 0);
        return Result.success();
    }

    @GetMapping("/self/superiorUser")
    @Operation(summary = "获取自己的上级")
    @AuthAccess(allowRoles = {"user"})
    public UserDto getSelfSuperiorUser(@RequestAttribute("userId") int userId) {
        User user = hierarchyService.getSuperiorUser(userId);
        if (user == null) {
            throw new ServiceException("您没有上级");
        }
        return new UserDto(user);
    }

    @GetMapping("/self/subordinateUser")
    @Operation(summary = "获取自己的下级")
    @AuthAccess(allowRoles = {"user"})
    public List<UserDto> getSelfSubordinateUser(@RequestAttribute("userId") int userId) {
        List<User> users = hierarchyService.getSubordinateUser(userId);
        if (users == null || users.isEmpty()) {
            throw new ServiceException("您没有下级");
        }
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }


}

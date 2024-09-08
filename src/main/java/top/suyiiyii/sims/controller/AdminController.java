package top.suyiiyii.sims.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.dto.UserDto;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;

import java.util.ArrayList;
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
    @Autowired
    ModelMapper modelMapper;
    @AuthAccess(allowRoles = {"admin"})
    @GetMapping("/findAllUsersWithRoles")
    public Result<List<UserDto>> findAllUsersWithRoles() {
        List<User> users = userService.selectAll();
        List<UserDto> UserDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = modelMapper.map(user, UserDto.class);
            userDto.setUserId(user.getStudentId());
            userDto.setRoles(roleService.getRolesById(user.getId()));
            UserDtos.add(userDto);
        }
        return Result.success(UserDtos);
    }

    @AuthAccess(allowRoles = {"admin"})
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> users = userService.selectAll();
        return Result.success(users);
    }

}

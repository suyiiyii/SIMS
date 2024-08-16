package top.suyiiyii.sims.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/15 15:36
 * @PackageName:top.suyiiyii.sims.dto
 * @ClassName: UserDto
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String grade;
    private String group;
    private List<String> roles; // 角色名称列表
}

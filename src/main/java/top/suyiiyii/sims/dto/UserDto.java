package top.suyiiyii.sims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.suyiiyii.sims.entity.User;

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
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private Integer studentId;
    private String username;
    private String grade;
    private String userGroup;
    private List<String> roles; // 角色名称列表

    public UserDto(User user) {
        this.userId = user.getId();
        this.studentId = user.getStudentId();
        this.username = user.getUsername();
        this.grade = user.getGrade();
        this.userGroup = user.getUserGroup();
    }
}

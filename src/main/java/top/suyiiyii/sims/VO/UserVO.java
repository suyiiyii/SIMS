package top.suyiiyii.sims.VO;

import lombok.Data;
import top.suyiiyii.sims.entity.Role;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/15 16:04
 * @PackageName:top.suyiiyii.sims.VO
 * @ClassName: UserVO
 * @Description: TODO
 * @Version 1.0
 */
@Data
public class UserVO {
    private Integer userId;
    private String username;
    private String grade;
    private String group;
    private List<String> roles; // 角色名称列表

}

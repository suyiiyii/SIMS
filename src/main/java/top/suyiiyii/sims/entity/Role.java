package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:02
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: Role
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
public class Role {

    private Integer id;
    private Integer roleId;
    //管理员，普通用户，组员，组长，队长
    private String roleName;

}

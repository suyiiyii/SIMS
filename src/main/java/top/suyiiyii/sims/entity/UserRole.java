package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:02
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: UserRole
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
public class UserRole {

    private Integer id;
    private Integer userId;
    private Integer roleId;
}

package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:03
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: Permissions
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
public class Permissions {

    private Integer id;
    //权限id
    private Integer permissionId;
    // 权限描述
    private String description;

}

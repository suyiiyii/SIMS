package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

import java.security.Permission;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:03
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: RolePermission
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
public class RolePermission {
    private Integer rolePermissionId;
    private Integer roleId;
    private Integer permissionId;
}

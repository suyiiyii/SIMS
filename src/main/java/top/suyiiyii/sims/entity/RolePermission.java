package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.autotable.annotation.ColumnNotNull;
import com.tangzc.mpe.autotable.annotation.ColumnId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
    @ColumnId(mode = IdType.AUTO,comment = "id主键")
    private Integer id;
    @ColumnNotNull
    private Integer roleId;
    @ColumnNotNull
    private Integer permissionId;
}

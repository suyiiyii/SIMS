package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/9 15:45
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: HierarchyRelation
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class HierarchyRelation {
    @TableId(type= IdType.AUTO)
    private Integer id;

    // 上级用户ID
    private Integer superiorUserId;

    // 下级用户ID
    private Integer subordinateUserId;

}

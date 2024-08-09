package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

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
public class HierarchyRelation {

    private Integer id;

    // 上级用户ID
    private Integer superiorUserId;

    // 下级用户ID
    private Integer subordinateUserId;

    // 关系类型
    private String relationType;
}

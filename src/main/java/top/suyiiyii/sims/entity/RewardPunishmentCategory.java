package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/9 15:43
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: RewardPunishmentCategory
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RewardPunishmentCategory {
    @TableId("id")
    private Integer id;

    private Integer categoryId;
    // 类别名称
    private String categoryName;

    private String subCategoryName;
    // 类别说明
    private String description;

}

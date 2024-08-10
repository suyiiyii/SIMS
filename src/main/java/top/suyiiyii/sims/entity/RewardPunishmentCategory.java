package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

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
public class RewardPunishmentCategory {

    private Integer id;

    private Integer categoryId;
    // 类别名称
    private String categoryName;

    private String subCategoryName;
    // 类别说明
    private String description;

}

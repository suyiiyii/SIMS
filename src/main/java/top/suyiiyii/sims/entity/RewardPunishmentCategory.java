package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.autotable.annotation.ColumnNotNull;
import com.tangzc.mpe.autotable.annotation.Table;
import com.tangzc.mpe.autotable.annotation.UniqueIndex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer categoryId;
    // 类别名称
    @ColumnNotNull
    private String categoryName;
    @ColumnNotNull
    @UniqueIndex
    private String subCategoryName;
    // 类别说明
    private String description;

    // 类别状态
    private String status;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardPunishmentCategory that = (RewardPunishmentCategory) o;
        return Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}

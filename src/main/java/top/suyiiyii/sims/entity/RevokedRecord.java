package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author tortoise
 * @Date 2024/8/10 0:34
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: RevokedRecord
 * @Description:  存储管理员对奖惩记录的撤销信息，包括撤销原因
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RevokedRecord {
    @TableId(type= IdType.AUTO)
    private Integer id;
    // 被撤销的奖惩记录ID
    private Integer recordId;
    // 撤销操作的管理员ID
    private Integer adminId;
    // 撤销原因
    private String reason;
    // 撤销时间
    private LocalDateTime revokedTime;

}


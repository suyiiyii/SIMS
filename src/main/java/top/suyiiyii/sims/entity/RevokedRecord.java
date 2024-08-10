package top.suyiiyii.sims.entity;

import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

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
public class RevokedRecord {

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


package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:04
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: RecordMapper
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @TableId(type = IdType.AUTO)
    //自增
    private Integer id;
    // 用户ID
    private Integer StudentId;
    // 奖惩类别ID
    private Integer categoryId;
    // 奖惩日期
    private Long date;
    // 奖惩内容
    private String content;
    // 奖惩原因
    private String reason;
    // 奖惩金额
    private Double amount;
    // 奖惩备注
    private String remark;
    // 是否撤销
    private Boolean isRevoked;
    // 撤销日期
    private Long revokeDate;
    // 撤销原因
    private String revokeReason;
    // 撤销备注
    private String revokeRemark;
    // 操作人ID
    private Integer operatorUserId;
    // 最近一次更新时间
    private Long lastUpdateTime;


}

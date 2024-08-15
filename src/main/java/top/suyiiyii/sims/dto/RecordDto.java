package top.suyiiyii.sims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private Integer id;
    // 用户ID
    private Integer userId;
    // 奖惩类型
    private String type;
    // 奖惩类别ID
    private Integer categoryId;
    // 奖惩日期
    private LocalDateTime date;
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
    private LocalDateTime revokeDate;
    // 撤销原因
    private String revokeReason;
    // 撤销备注
    private String revokeRemark;
    // 操作人ID
    private Integer operatorUserId;
    // 最近一次更新时间
    private LocalDateTime lastUpdateTime;
}

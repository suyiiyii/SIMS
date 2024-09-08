package top.suyiiyii.sims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {

    //
    private Integer id;
    // 用户ID
    private Integer studentId;

    private String categoryName;

    private String subCategoryName;
    // 类别id
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

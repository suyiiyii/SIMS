package top.suyiiyii.sims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/29 21:02
 * @PackageName:top.suyiiyii.sims.dto
 * @ClassName: UserRecordDto
 * @Description: TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRecordDto {
    // 用户ID
    private Integer studentId;


    private String categoryName;

    private String subCategoryName;
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

}

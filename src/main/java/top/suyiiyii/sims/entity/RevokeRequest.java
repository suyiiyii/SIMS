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
 * @Date 2024/8/10 0:31
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: RevokeRequest
 * @Description:  存储普通成员提出的奖惩撤销申请，并跟踪申请状态
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RevokeRequest {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer recordId;
    private Integer userId;
    private String reason;
    private LocalDateTime requestTime;
    private String status;
    //处理时间
    private LocalDateTime handleTime;
    private String adminRemark;

}


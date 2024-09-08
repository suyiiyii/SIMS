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
 * @Date 2024/8/9 22:22
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: Notification
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer senderId;
    private LocalDateTime createdAt;
    private String status;
    private String type;
    private Integer targetUserId;

}

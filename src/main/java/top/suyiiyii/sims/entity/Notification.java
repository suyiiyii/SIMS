package top.suyiiyii.sims.entity;

import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;

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
public class Notification {
    private Integer id;
    private String title;
    private String content;
    private Integer senderId;
    private LocalDateTime createdAt;
    private String status;
    private String type;
    private Integer targetUserId;

}

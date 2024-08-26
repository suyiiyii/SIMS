package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.tangzc.mpe.autotable.annotation.Column;
import com.tangzc.mpe.autotable.annotation.ColumnId;

import com.tangzc.mpe.autotable.annotation.Table;
import com.tangzc.mpe.autotable.annotation.UniqueIndex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:02
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: User
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ColumnId(mode = IdType.AUTO, comment = "id主键")

    private Integer id;
    @UniqueIndex
    @Column(comment = "学生id", notNull = true)
    private Integer studentId;
    @UniqueIndex
    @Column(comment = "用户名", notNull = true)
    private String username;
    @Column(comment = "密码", notNull = true)
    private String password;
    @UniqueIndex
    @Column(comment = "邮箱", notNull = true)
    private String email;
    @UniqueIndex
    @Column(comment = "年级", notNull = true)
    private String grade;
    @UniqueIndex
    @Column(comment = "用户所属团队", notNull = true)
    private String userGroup;
}

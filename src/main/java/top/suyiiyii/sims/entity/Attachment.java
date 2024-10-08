package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/9 15:44
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: Attachment
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private Integer recordId;
    // 文件路径
    private String filePath;
    // 文件名
    private String fileName;
}

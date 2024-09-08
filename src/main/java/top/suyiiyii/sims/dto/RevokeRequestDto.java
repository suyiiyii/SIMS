package top.suyiiyii.sims.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/9/8 21:34
 * @PackageName:top.suyiiyii.sims.dto
 * @ClassName: RevokeRequestDto
 * @Description: TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevokeRequestDto {
    private Integer id;
    private String categoryName;
    private String subCategoryName;
    private Integer userId;
    private String reason;
    private Long requestTime;
    private String status;
    //处理时间
    private Long handleTime;
    private String adminRemark;

}

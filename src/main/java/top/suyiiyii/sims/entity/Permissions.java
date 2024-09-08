package top.suyiiyii.sims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Author tortoise
 * @Date 2024/8/9 14:03
 * @PackageName:top.suyiiyii.sims.entity
 * @ClassName: Permissions
 * @Description: TODO
 * @Version 1.0
 */
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Permissions {
    @TableId(type= IdType.AUTO)
    private Integer id;
    //权限id
    private Integer permissionId;

    private String path;
    // 权限描述
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permissions that = (Permissions) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}

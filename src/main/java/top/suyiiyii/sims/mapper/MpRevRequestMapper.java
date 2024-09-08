package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.suyiiyii.sims.entity.RevokeRequest;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/9/6 10:04
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: MpRevRequestMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface MpRevRequestMapper extends BaseMapper<RevokeRequest> {
    @Select("select * from revoke_request limit #{page},#{size}")
    List<RevokeRequest> selectList(int page, int size);
}

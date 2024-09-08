package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
@Update("update revoke_request set status=#{status},admin_remark=#{adminRemark} where id=#{id}")
    void update(Integer id, String status, String adminRemark);
@Update("update revoke_request set status=#{status},admin_remark=#{adminRemark},reason=#{reason},handle_time=#{handleTime} where id=#{id}")
    void update(Integer id, String status, String adminRemark, String reason, Long handleTime);
}

package top.suyiiyii.sims.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import top.suyiiyii.sims.entity.RevokeRequest;
import top.suyiiyii.sims.entity.RevokedRecord;

/**
 * @Author tortoise
 * @Date 2024/9/9 10:08
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: MpRevRecord
 * @Description: TODO
 * @Version 1.0
 */
public interface MpRevRecord extends BaseMapper<RevokedRecord> {
    @Insert("INSERT INTO revoked_record (admin_id, reason, revoked_time, record_id) VALUES (#{userId}, #{reason}, #{handleTime}, #{id})")
    void addRevokedRecord(Integer id, String userId, String reason, Long handleTime);
}

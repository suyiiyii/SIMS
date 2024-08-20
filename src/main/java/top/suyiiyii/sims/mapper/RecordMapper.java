package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.*;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/16 21:39
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: RecordMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface RecordMapper {
    //分页查询
@Select("select * from record limit #{page},#{size}")
    List<Record> getAllRecords(Integer page, Integer size);
//根据学号分页查询所以信息
    @Select("select * from record where student_id = #{id} limit #{page},#{size}")
    List<Record> getMyAllRecords(Integer page, Integer size, String id);

    //根据id，更新对应信息
    @Update("UPDATE record SET "

            + "date = #{record.date}, "
            + "content = #{record.content}, "
            + "reason = #{record.reason}, "
            + "amount = #{record.amount}, "
            + "remark = #{record.remark}, "
            + "is_revoked = #{record.isRevoked}, "
            + "revoke_date = #{record.revokeDate}, "
            + "revoke_reason = #{record.revokeReason}, "
            + "revoke_remark = #{record.revokeRemark}, "
            + "operator_user_id = #{record.operatorUserId}, "
            + "last_update_time = #{record.lastUpdateTime} "
            + "WHERE id = #{id}")
    void updateRecord(Record record, Integer id);

    @Delete("delete from record where id = #{id}")
    void deleteRecord(Integer id);
    @Insert({
            "insert into record (student_id, category_id, `date`, content, reason, amount, remark, is_revoked,",
            "revoke_date, revoke_reason, revoke_remark, operator_user_id, last_update_time)",
            "VALUES (#{studentId}, #{categoryId}, #{date}, #{content}, #{reason}, #{amount}, #{remark}, #{isRevoked},",
            "#{revokeDate}, #{revokeReason}, #{revokeRemark}, #{operatorUserId}, #{lastUpdateTime})"
    })
    void addRecord(Record record);
}

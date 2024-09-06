package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.*;
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

    @Select({
            "<script>",
            "SELECT * FROM record WHERE 1=1",
            "<if test='studentId != null '>",
            "AND student_id = #{studentId}",
            "</if>",
            "<if test='userGroup != null and userGroup.trim() != \"\"'>",
            "AND user_group LIKE CONCAT('%', #{userGroup}, '%')",
            "</if>",
            "<if test='grade != null and grade.trim() != \"\"'>",
            "AND grade LIKE CONCAT('%', #{grade}, '%')",
            "</if>",
            "LIMIT #{size}, #{page}",
            "</script>"
    })
    List<Record> getRecordsLike(
            @Param("size") int size,
            @Param("page") int page,

            @Param("studentId") Integer studentId,
            @Param("userGroup") String userGroup,
            @Param("grade") String grade
    );



@Select("select student_id from record where category_id = #{i}")
    List<Integer> getSidByCategoryId(Integer i);
@Select("SELECT * FROM record WHERE student_id = #{sid} LIMIT #{page},#{size}")
    List<Record> getRecordsById(int page, int size, Integer sid);
@Select("SELECT id FROM record WHERE id = #{id}")
    Integer IsRecord(Integer id);
}

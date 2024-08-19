package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}

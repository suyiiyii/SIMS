package top.suyiiyii.sims.service;


import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.mapper.RecordMapper;
import top.suyiiyii.sims.mapper.UserMapper;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/16 21:47
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: RecordService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    UserMapper userMapper;

    public List<Record> getAllRecords(Integer page, Integer size) {

        return recordMapper.getAllRecords(page, size);
    }


    public List<Record> getMyAllRecords(Integer page, Integer size, String userId) {
        String studentId = userMapper.getStudentIdById(userId);
        return recordMapper.getMyAllRecords(page, size, studentId);
    }

    public void updateRecord(Record record, Integer id) {
        recordMapper.updateRecord(record, id);
    }

    public void deleteRecord(Integer id) {
        recordMapper.deleteRecord(id);
    }

    public void addRecord(Record record) {
        recordMapper.addRecord(record);
    }
}

package top.suyiiyii.sims.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.mapper.RecordMapper;

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


    public List<Record> getAllRecords(Integer page, Integer size) {

        return recordMapper.getAllRecords(page, size);
    }
}

package top.suyiiyii.sims.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.mapper.CategoryMapper;
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
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryMapper categoryMapper;
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

    public void addRecord(RecordDto recordDto) {
        //把recordDto转化成Record
        recordDto.setId(null);
        Record record = modelMapper.map(recordDto, Record.class);

        //查看数据库里面是否有这个类别
        String subCategoryName = categoryMapper.IsSubCategoryName(recordDto.getSubCategoryName());
        if(subCategoryName == null) {
            //没有这个类别就加上
            categoryMapper.addsubcategory(recordDto.getCategoryName(), recordDto.getSubCategoryName());
        }
        Integer categoryId = categoryMapper.getIdBySubCategoryName(recordDto.getSubCategoryName());
        categoryMapper.addCategoryId(categoryId);
        record.setCategoryId(categoryId);
        recordMapper.addRecord(record);

    }

    public List<Record> getRecordsLike(int page, int size, Integer studentId, String userGroup, String grade) {
        return recordMapper.getRecordsLike(page, size, studentId, userGroup,grade);
    }



    public  List<Integer> getSidByCategoryId(Integer i) {
        return recordMapper.getSidByCategoryId(i);
    }

    public List<Record> getRecordsById(int page, int size, Integer sid) {
        return recordMapper.getRecordsById(page, size, sid);
    }

    public Integer IsRecord(Integer id) {
        return recordMapper.IsRecord(id);
    }
}

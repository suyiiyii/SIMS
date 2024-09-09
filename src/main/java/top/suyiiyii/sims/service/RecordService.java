package top.suyiiyii.sims.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.entity.RewardPunishmentCategory;
import top.suyiiyii.sims.mapper.CategoryMapper;
import top.suyiiyii.sims.mapper.MpCategoryMapper;
import top.suyiiyii.sims.mapper.RecordMapper;
import top.suyiiyii.sims.mapper.UserMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    MpCategoryMapper mpCategoryMapper;

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

    public List<Record> filterRecords(List<Record> records) {
        List<Integer> catIds = records.stream().map(Record::getCategoryId).distinct().toList();
        List<RewardPunishmentCategory> categories = mpCategoryMapper.selectBatchIds(catIds);
        Map<Integer, RewardPunishmentCategory> catMap = categories.stream().collect(Collectors.toMap(RewardPunishmentCategory::getId, c -> c));
        List<Integer> availableCatIds = catIds.stream().filter(c -> (catMap.containsKey(c) && catMap.get(c).getStatus() == null)).toList();
        return records.stream().filter(r -> availableCatIds.contains(r.getCategoryId())).toList();
    }

    public List<RecordDto> filterRecordsDtos(List<RecordDto> recordDtos) {
        List<Integer> catIds = recordDtos.stream().map(RecordDto::getCategoryId).distinct().toList();
        List<RewardPunishmentCategory> categories = mpCategoryMapper.selectBatchIds(catIds);
        Map<Integer, RewardPunishmentCategory> catMap = categories.stream().collect(Collectors.toMap(RewardPunishmentCategory::getId, c -> c));
        List<Integer> availableCatIds = catIds.stream().filter(c -> (catMap.containsKey(c) && (catMap.get(c).getStatus() == null || catMap.get(c).getStatus().equals("enable")))).toList();
        return recordDtos.stream().filter(r -> availableCatIds.contains(r.getCategoryId())).toList();
    }

    public void update(Integer id, String userId, String adminRemark, String reason, Long handleTime) {
        Boolean isRevoked=true;
        recordMapper.update(id, isRevoked,userId, adminRemark, reason, handleTime);
    }

    public void revokeUpdate(Integer id, String reason,String userId) {
        String revokeReason="申请撤销";
        Boolean isRevoked=true;
        recordMapper.Rupdate(id, reason,isRevoked,revokeReason,userId);
    }

    public Integer getCategoryIdById(Integer  id) {
        return recordMapper.getCategoryIdById(id);
    }

    public List<Record> getRecordsByCategoryId(int page, int size, Integer i) {
        return recordMapper.getRecordsByCategoryId(page, size, i);
    }
}

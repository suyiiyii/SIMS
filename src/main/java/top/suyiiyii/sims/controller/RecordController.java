package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;

import top.suyiiyii.sims.service.CategoryService;
import top.suyiiyii.sims.service.RecordService;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;
import top.suyiiyii.sims.utils.JwtUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class
RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMapper modelMapper;

    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "获取所有奖惩记录")
    @GetMapping("/admin/record")
    public Result<List<RecordDto>> adminRecord(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Record> records = recordService.getAllRecords(page, size);
        List<RecordDto> recordDtos = new ArrayList<>();
        for (Record record : records) {
            RecordDto recordDto = modelMapper.map(record, RecordDto.class);
            recordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            recordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            recordDtos.add(recordDto);
        }
        return Result.success(recordDtos);
    }

    @AuthAccess(allowRoles = {"user"})
    @Operation(summary = "获取自己的奖惩记录")
    @GetMapping("/record")
    public Result<List<RecordDto>> record(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        String userId = JwtUtils.extractUserId(token);
        List<RecordDto> recordDtos = new ArrayList<>();
        List<Record> records = recordService.getMyAllRecords(page, size, userId);
        for (Record record : records) {
            RecordDto recordDto = modelMapper.map(record, RecordDto.class);
            recordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            recordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            recordDtos.add(recordDto);
        }
        return Result.success(recordDtos);
    }
    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "更新单个奖惩记录")
    @PutMapping("/admin/record/{id}")
    public Result<CommonResponse> adminUpdateRecord(@PathVariable Integer id, @RequestBody RecordDto recordDto) {
        Record record = modelMapper.map(recordDto, Record.class);
        Integer i = recordService.IsRecord(id);
        if(i==null) {
            throw new RuntimeException("该记录不存在");
        }
        recordService.updateRecord(record, id);
        return Result.msg("修改成功");
    }

    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "删除单个奖惩记录")
    @DeleteMapping("/admin/record/{id}")
    public Result<CommonResponse> adminDeleteRecord(@PathVariable Integer id) {
        Integer i = recordService.IsRecord(id);
        if(i==null) {
            throw new RuntimeException("该记录不存在");
        }
        recordService.deleteRecord(id);
        return Result.msg("删除成功");
    }


    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "添加奖惩记录")
    @PostMapping("/admin/record")
    public Result<CommonResponse> adminAddRecord(@RequestBody RecordDto recordDto) {
//CategoryName不是奖励或者惩罚
        if (!recordDto.getCategoryName().equals("奖励")
                && !recordDto.getCategoryName().equals("惩罚")) {
            return Result.error("请选择正确奖惩类别");
        }
        if (recordDto.getSubCategoryName().isEmpty()) {
            return Result.error("请输入奖惩类型");
        }

        recordService.addRecord(recordDto);
        return Result.msg("添加成功");
    }
   @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "模糊查询奖惩记录")
    @GetMapping("/admin/likeRecords")
    public Result<List<RecordDto>> searchRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            String username,Integer studentId, String userGroup, String grade,String roleName) {
        Integer s1;
        List<Integer> studentIds = new ArrayList<>();
        List<Record> records=new ArrayList<>();
        if(studentId!=null) {
            studentIds.add(studentId);
        }
        if(roleName!="") {
            //rolename查用户id
            Integer userId = roleService.getIdByrolename(roleName);
         //     用户id查记录
          s1 = userService.getStudentIdByUserId(userId);
            studentIds.add(s1);
        }
        if(username!="") {
            //username查用户StudentId
            s1= roleService.getStudentIdByUsername(username);
            studentIds.add(s1);
        }
        for (Integer Sid : studentIds) {
            records.addAll(recordService.getRecordsLike(page,size,Sid,userGroup,grade));
        }
        List<RecordDto> RecordDtos = new ArrayList<>();
        for (Record record : records) {
            RecordDto RecordDto = modelMapper.map(record, RecordDto.class);
            RecordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            RecordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            RecordDtos.add(RecordDto);
        }
        return Result.success(RecordDtos);
    }
    @AuthAccess(allowRoles = {"admin"})
    @Operation(summary = "筛选查询奖惩记录")
    @GetMapping("/admin/screenRecords")
    public Result<List<RecordDto>> screenRecords(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            String categoryName) {
        List<Integer> studentIds = new ArrayList<>();
        //CategoryName不是奖励或者惩罚
        if (!categoryName.equals("奖励")
                && !categoryName.equals("惩罚")) {
            return Result.error("请选择正确奖惩类别");
        }
        List<Integer> idByCategoryName = categoryService.getIdByCategoryName(categoryName);
        for (Integer i : idByCategoryName) {
            Integer sid = recordService.getSidByCategoryId(i);
            studentIds.add(sid);
        }
        List<Record> records=new ArrayList<>();
        HashSet<Integer> studentIds1= new HashSet<>(studentIds);
        for (Integer Sid : studentIds1) {
            if(Sid!=null){
                records.addAll(recordService.getRecordsById(page,size,Sid));
            }
        }
        List<RecordDto> RecordDtos = new ArrayList<>();
        for (Record record : records) {
            RecordDto RecordDto = modelMapper.map(record, RecordDto.class);
            RecordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            RecordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            RecordDtos.add(RecordDto);
        }
        return Result.success(RecordDtos);
    }

    @AuthAccess(allowRoles = {"user","admin"})
    @Operation(summary = "用户筛选查询奖惩记录")
    @GetMapping("/screenRecords")
    public Result<List<RecordDto>> screenRecords1(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            String categoryName,HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        String userId = JwtUtils.extractUserId(token);
        if (userId==null){
            throw new RuntimeException("请先登录");
        }
        List<Integer> studentIds = new ArrayList<>();
        //CategoryName不是奖励或者惩罚
        if (!categoryName.equals("奖励")
                && !categoryName.equals("惩罚")) {
            return Result.error("请选择正确奖惩类别");
        }
        List<Integer> idByCategoryName = categoryService.getIdByCategoryName(categoryName);
        for (Integer i : idByCategoryName) {
            Integer sid = recordService.getSidByCategoryId(i);
            if(sid!=null) {
                studentIds.add(sid);
            }
        }
        List<Record> records=new ArrayList<>();
        HashSet<Integer> studentIds1= new HashSet<>(studentIds);
        for (Integer Sid : studentIds1) {
            Integer studentId1 =userService.getStudentIdByUserId(Integer.valueOf(userId));
            if (studentId1!= null && studentId1.equals(Sid)) {
                records.addAll(recordService.getRecordsById(page, size, Sid));
            }
        }
        List<RecordDto> RecordDtos = new ArrayList<>();
        for (Record record : records) {
            RecordDto RecordDto = modelMapper.map(record, RecordDto.class);
            RecordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            RecordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            RecordDtos.add(RecordDto);
        }
        return Result.success(RecordDtos);
    }
}

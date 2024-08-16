package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.entity.UserRole;
import top.suyiiyii.sims.mapper.CategoryMapper;
import top.suyiiyii.sims.mapper.UserMapper;
import top.suyiiyii.sims.service.CategoryService;
import top.suyiiyii.sims.service.RecordService;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class
RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    UserService UserService;
    @Autowired
    RoleService roleService;
    @Autowired
    CategoryService categoryService;

    @Operation(summary = "获取所有奖惩记录")
    @GetMapping("/admin/record")
    public Result<List<RecordDto>> adminRecord(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<RecordDto> recordDtos=new ArrayList<>();

        List<Record> records = recordService.getAllRecords(page, size);
        for (Record record : records) {
            RecordDto recordDto = new RecordDto();
            Integer studentId=record.getStudentId();
            recordDto.setStudentId(studentId);
            User user = UserService.selectByUserId(studentId);
            recordDto.setName(user.getUsername());
            recordDto.setGrade(user.getUserGroup());
            recordDto.setGroup(user.getUserGroup());
            List<Role> roles = UserService.selectRolesById(studentId);
            ArrayList<String> roleName = new ArrayList<>();
            for (Role role : roles) {
                roleName.add(role.getRoleName());
            }
            recordDto.setRoles(roleName);
            recordDto.setCategoryName(categoryService.getCategoryName(record.getCategoryId()));
            recordDto.setSubCategoryName(categoryService.getsubCategoryName(record.getCategoryId()));
            recordDto.setDate(record.getDate());
            recordDto.setContent(record.getContent());
            recordDto.setReason(record.getReason());
            recordDto.setContent(record.getContent());
            recordDto.setAmount(record.getAmount());
            recordDto.setRemark(record.getRemark());
            recordDto.setIsRevoked(record.getIsRevoked());
            // 撤销日期
            recordDto.setRevokeDate(record.getRevokeDate());
            recordDto.setRevokeReason(record.getRevokeReason());
            recordDto.setRevokeRemark(record.getRevokeRemark());
            recordDto.setOperatorUserId(record.getOperatorUserId());
            recordDto.setLastUpdateTime(record.getLastUpdateTime());
            recordDtos.add(recordDto);
        }


        return Result.success(recordDtos);
    }

    @Operation(summary = "获取自己的奖惩记录")
    @GetMapping("/record")
    public Result<List<RecordDto>> record(Integer page, Integer size) {
        return Result.success(new ArrayList<>());
    }

    @Operation(summary = "更新单个奖惩记录")
    @PutMapping("/admin/record/{id}")
    public Result<CommonResponse> adminUpdateRecord(@PathVariable Integer id, @RequestBody RecordDto recordDto) {

        return Result.msg("修改成功");
    }

    @Operation(summary = "删除单个奖惩记录")
    @DeleteMapping("/admin/record/{id}")
    public Result<CommonResponse> adminDeleteRecord(@PathVariable Integer id) {

        return Result.msg("删除成功");
    }


    @Operation(summary = "添加奖惩记录")
    @PostMapping("/admin/record")
    public Result<CommonResponse> adminAddRecord(@RequestBody RecordDto recordDto) {

        return Result.msg("添加成功");
    }


}

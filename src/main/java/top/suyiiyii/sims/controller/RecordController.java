package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.RecordDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordController {


    @Operation(summary = "获取所有奖惩记录")
    @GetMapping("/admin/record")
    public Result<List<RecordDto>> adminRecord(Integer page, Integer size) {
        return Result.success(new ArrayList<>());
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

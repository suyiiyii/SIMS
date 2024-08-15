package top.suyiiyii.sims.controller;

import org.springframework.web.bind.annotation.*;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.dto.CommonResponse;
import top.suyiiyii.sims.dto.RecordDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordController {


    @GetMapping("/admin/record")
    public Result<List<RecordDto>> adminRecord(Integer page, Integer size) {
        return Result.success(new ArrayList<>());
    }

    @GetMapping("/record")
    public Result<List<RecordDto>> record(Integer page, Integer size) {
        return Result.success(new ArrayList<>());
    }

    @PutMapping("/admin/record/{id}")
    public Result<CommonResponse> adminUpdateRecord(@PathVariable Integer id, @RequestBody RecordDto recordDto) {

        return Result.msg("修改成功");
    }

    @DeleteMapping("/admin/record/{id}")
    public Result<CommonResponse> adminDeleteRecord(@PathVariable Integer id) {

        return Result.msg("删除成功");
    }


    @PostMapping("/admin/record")
    public Result<CommonResponse> adminAddRecord(@RequestBody RecordDto recordDto) {

        return Result.msg("添加成功");
    }


}

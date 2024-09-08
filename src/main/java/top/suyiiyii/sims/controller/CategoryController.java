package top.suyiiyii.sims.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.common.Result;
import top.suyiiyii.sims.entity.RewardPunishmentCategory;
import top.suyiiyii.sims.service.CategoryService;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    @Operation(summary = "获取所有类别信息，支持分页")
    @AuthAccess(allowRoles = {"admin"})
    public Result<List<RewardPunishmentCategory>> getAllCategory(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Page<RewardPunishmentCategory> pageObj = new Page<>(page, size);
        return Result.success(categoryService.getCateGory(pageObj));
    }

}

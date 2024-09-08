package top.suyiiyii.sims.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @Operation(summary = "获取所有类别",description = "获取所有类别信息，支持分页")
    @AuthAccess(allowRoles = {"admin"})
    public Result<List<RewardPunishmentCategory>> getAllCategory(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        Page<RewardPunishmentCategory> pageObj = new Page<>(page, size);
        return Result.success(categoryService.getCateGory(pageObj));
    }

    @PostMapping("")
    @Operation(summary = "添加类别")
    @AuthAccess(allowRoles = {"admin"})
    public Result<String> addCategory(@RequestBody RewardPunishmentCategory category) {
        category.setId(null);
        categoryService.addCategory(category);
        return Result.success("添加成功");
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新类别",description = """
            根据id更新类别信息\n当status为"disable"时，表示禁用该类别\n当status为null或"enable"时，表示启用该类别""")
    @AuthAccess(allowRoles = {"admin"})
    public Result<String> updateCategory(@RequestBody RewardPunishmentCategory category,@PathVariable Integer id) {
        category.setId(id);
        category.setCategoryId(id);
        categoryService.updateCategory(category);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除类别",description = "根据id删除类别")
    @AuthAccess(allowRoles = {"admin"})
    public Result<String> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return Result.success("删除成功");
    }

}

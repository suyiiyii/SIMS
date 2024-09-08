package top.suyiiyii.sims.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.entity.Record;
import top.suyiiyii.sims.entity.RewardPunishmentCategory;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.CategoryMapper;
import top.suyiiyii.sims.mapper.MpCategoryMapper;
import top.suyiiyii.sims.mapper.MpRecordMapper;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2024/8/16 23:32
 * @PackageName:top.suyiiyii.sims.service
 * @ClassName: CategoryService
 * @Description: TODO
 * @Version 1.0
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    MpCategoryMapper mpCategoryMapper;

    @Autowired
    MpRecordMapper mpRecordMapper;


    public String getCategoryName(Integer id) {
        return categoryMapper.getCategoryName(id);
    }

    public String getsubCategoryName(Integer categoryId) {
        return categoryMapper.getSubCategoryName(categoryId);
    }


    public List<Integer> getIdByCategoryName(String categoryName) {
        return categoryMapper.getIdByCategoryName(categoryName);
    }


    public List<RewardPunishmentCategory> getCateGory(Page pageObj) {
        mpCategoryMapper.selectPage(pageObj, new LambdaQueryWrapper<>());
        return pageObj.getRecords();
    }

    public List<RewardPunishmentCategory> getCateByIds(List<Integer> ids) {
        return mpCategoryMapper.selectBatchIds(ids);
    }

    public void updateCategory(RewardPunishmentCategory category) {
        if (!mpCategoryMapper.exists(new LambdaQueryWrapper<RewardPunishmentCategory>().eq(RewardPunishmentCategory::getId, category.getId()))) {
            throw new ServiceException("该类别不存在");
        }
        RewardPunishmentCategory cateInDb = mpCategoryMapper.selectById(category.getId());
        category.setId(cateInDb.getId());
        category.setCategoryId(cateInDb.getCategoryId());
        mpCategoryMapper.updateById(category);
    }

    public void addCategory(RewardPunishmentCategory category) {
        mpCategoryMapper.insert(category);
        category.setCategoryId(category.getId());
        mpCategoryMapper.updateById(category);
    }

    public void deleteCategory(Integer id, Integer newId) {
        if (!mpCategoryMapper.exists(new LambdaQueryWrapper<RewardPunishmentCategory>().eq(RewardPunishmentCategory::getId, id))) {
            throw new ServiceException("该类别不存在");
        }
        mpCategoryMapper.deleteById(id);
        mpRecordMapper.update(new LambdaUpdateWrapper<Record>().eq(Record::getCategoryId, id).set(Record::getCategoryId, newId));
    }
}

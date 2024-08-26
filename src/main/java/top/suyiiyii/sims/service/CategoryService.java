package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.mapper.CategoryMapper;

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


    public String getCategoryName(Integer id) {
        return categoryMapper.getCategoryName(id);
    }

    public String getsubCategoryName(Integer categoryId) {
        return categoryMapper.getSubCategoryName(categoryId);
    }


    public Integer getIdBySubCategoryName(String subCategoryName) {
        return categoryMapper.getIdBySubCategoryName(subCategoryName);

    }
}

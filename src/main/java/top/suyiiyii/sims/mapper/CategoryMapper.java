package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Author tortoise
 * @Date 2024/8/16 23:31
 * @PackageName:top.suyiiyii.sims.mapper
 * @ClassName: CategoryMapper
 * @Description: TODO
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM reward_punishment_category WHERE category_id=#{id}")
    String getCategoryName(Integer categoryId);

    @Select("SELECT category_name FROM reward_punishment_category WHERE category_id=#{categoryId}")
    String getSubCategoryName(Integer categoryId);

    @Select("SELECT category_id FROM reward_punishment_category WHERE sub_category_name=#{subCategoryName}")
    Integer getIdBySubCategoryName(String subCategoryName);
}

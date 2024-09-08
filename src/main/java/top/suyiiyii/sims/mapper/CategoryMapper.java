package top.suyiiyii.sims.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
    @Select("SELECT category_name FROM reward_punishment_category WHERE category_id=#{id}")
    String getCategoryName(Integer categoryId);

    @Select("SELECT sub_category_name FROM reward_punishment_category WHERE category_id=#{categoryId}")
    String getSubCategoryName(Integer categoryId);

    @Select("SELECT id FROM reward_punishment_category WHERE sub_category_name=#{subCategoryName}")
    Integer getIdBySubCategoryName(String subCategoryName);
    @Select("SELECT category_id FROM reward_punishment_category WHERE category_name=#{categoryName}")
    List<Integer> getIdByCategoryName(String categoryName);
    @Select("SELECT sub_category_name FROM reward_punishment_category WHERE sub_category_name=#{subCategoryName}")
    String IsSubCategoryName(String subCategoryName);
@Insert("INSERT INTO reward_punishment_category (category_name, sub_category_name) VALUES (#{categoryName}, #{subCategoryName})")
    void addsubcategory(String categoryName, String subCategoryName);
//把categoryId放入对应id下
@Update("update reward_punishment_category set category_id=#{categoryId} where id=#{categoryId}")
    void addCategoryId(Integer categoryId);
}

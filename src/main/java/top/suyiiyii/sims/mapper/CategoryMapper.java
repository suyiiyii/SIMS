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
    @Select("SELECT * FROM category WHERE categoryId=#{id}")
    String getCategoryName(Integer categoryId);

    @Select("SELECT categoryName FROM category WHERE categoryId=#{categoryId}")

        String getSubCategoryName(Integer categoryId);
}

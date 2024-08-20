package top.suyiiyii.sims.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.entity.User;

/**
 * @Author tortoise
 * @Date 2024/8/20 21:19
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: ModelMapperConfig
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 设置完全匹配
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);

        // 设置匹配策略为严格模式
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
      //  configureUser(modelMapper);
        return  modelMapper;
    }
    // 配置 User 类的映射规则
    private void configureUser(ModelMapper modelMapper) {
        // 定义 UserModel -> User 的映射规则
   //     modelMapper.typeMap(RecordDto.class, Record.class)

        // 跳过设置密码字段
        // 定义 User -> UserModel 的映射规则
      //  modelMapper.typeMap(User.class, RecordDto.class)
      //          .addMappings(mapper -> mapper.skip(RecordDto::setPassword))  // 跳过设置密码字段
      //          .addMappings(mapper -> mapper.map(User::getRealName, UserModel::setName)); // 将 User 的 realName 映射为 UserModel 的 name
//                .addMappings(mapper -> mapper.using(dateToStringConverter).map(User::getCreateTime, UserModel::setCreateTime))
//                .addMappings(mapper -> mapper.using(dateToStringConverter).map(User::getUpdateTime, UserModel::setUpdateTime));
    }
}

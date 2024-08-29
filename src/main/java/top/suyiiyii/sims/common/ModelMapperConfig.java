package top.suyiiyii.sims.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.suyiiyii.sims.dto.RecordDto;
import top.suyiiyii.sims.dto.UserRecordDto;

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
       // configureUser(modelMapper);
        return modelMapper;
    }


    private void configureUser(ModelMapper modelMapper) {

        // 跳过设置密码字段
//定义 Record -> UserRecordDto 的映射规则
 //modelMapper.typeMap(Record.class, UserRecordDto.class)
 //        .addMappings(mapper -> mapper.skip(UserRecordDto::setRevokeDate))
 //        .addMappings(mapper -> mapper.skip(UserRecordDto::setRevokeReason))
 //        .addMappings(mapper -> mapper.skip(UserRecordDto::setRevokeRemark))
 //        .addMappings(mapper -> mapper.skip(UserRecordDto::setOperatorUserId))
 //        .addMappings(mapper -> mapper.skip(UserRecordDto::setLastUpdateTime));
    }
}

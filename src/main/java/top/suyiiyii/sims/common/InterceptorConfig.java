package top.suyiiyii.sims.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.suyiiyii.sims.service.RoleService;

/**
 * @Author tortoise
 * @Date 2024/8/12 11:27
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: InterceptorConfig
 * @Description: TODO 拦截器配置
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Autowired
    private RbacInterceptor rbacInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/v3/api-docs/**");
        registry.addInterceptor(rbacInterceptor)
                .excludePathPatterns("/v3/api-docs/**");

        super.addInterceptors(registry);
    }


}


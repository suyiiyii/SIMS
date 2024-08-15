package top.suyiiyii.sims.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(jwtInterceptor())
//                    .addPathPatterns("/**")
//                    .excludePathPatterns("/user/login") // 排除不需要验证的路径
//                    .excludePathPatterns("/user/register")
//                    .excludePathPatterns("/v3/api-docs/**");

            super.addInterceptors(registry);
        }

        @Bean
        public JwtInterceptor jwtInterceptor() {
            return new JwtInterceptor();
        }

    }


package top.suyiiyii.sims.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.suyiiyii.sims.service.RoleService;
import top.suyiiyii.sims.service.UserService;
import top.suyiiyii.sims.utils.JwtUtils;

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
    private  RoleService roleService;



    //UserService userService;
        @Override
        protected void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(jwtInterceptor())
                   .addPathPatterns("/**")
                   .excludePathPatterns("/user/login") // 排除不需要验证的路径
                   .excludePathPatterns("/user/register")
                   .excludePathPatterns("/v3/api-docs/**");

            // 注册AdminInterceptor，只拦截以admin/开头的路径
            registry.addInterceptor(new AdminInterceptor())
                    .addPathPatterns("/admin/**");
            super.addInterceptors(registry);
        }

        @Bean
        public JwtInterceptor jwtInterceptor() {
            return new JwtInterceptor();
        }

    // AdminInterceptor的实现
    public class AdminInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String path = request.getRequestURI();
            if (path.startsWith("/admin/") && !hasAdminPermission(request)) {
                // 如果用户没有管理员权限，返回403 Forbidden
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
            return true;
        }

        private boolean hasAdminPermission(HttpServletRequest request) {
            // 这里应该实现检查用户权限的逻辑
            // 例如，从session、token或者数据库中获取用户信息并判断权限
            // 以下仅为示例
            String token = (String) request.getAttribute("token");
            //非空
            if (token == null) {
                return false;
            }
            try {
                Integer userId = Integer.valueOf(JwtUtils.extractUserId(token));
                return roleService.isRoleNameAdmin(userId);
            } catch (Exception e) {
                // 处理令牌解析过程中可能出现的异常
                return false;
            }
        }
    }
    }


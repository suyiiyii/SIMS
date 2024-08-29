package top.suyiiyii.sims.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.service.RbacService;

import java.util.List;

/**
 * Rbac 拦截器
 * 从请求对象中获取用户信息，然后判断用户是否有权限访问当前路径
 */
@Component
public class RbacInterceptor implements HandlerInterceptor {

    @Autowired
    RbacService rbacService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("/error".equals(request.getRequestURI())) {
            return true;
        }
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }
        // 获取用户角色
        List<String> roles = getUserRole(request).stream().map(Role::getRoleName).toList();

        List<String> allowRoles = null;

        // 获取当前请求的方法上的 AuthAccess 注解，从而获取允许访问的角色
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                allowRoles = List.of(annotation.allowRoles());
            }
        }

        if (allowRoles != null && !allowRoles.isEmpty()) {
            if (allowRoles.contains("guest")) {
                return true;
            }
            for (String role : roles) {
                if (allowRoles.contains(role)) {
                    return true;
                }
            }
        }
        throw new ServiceException("403", "权限不足");
    }

    private List<Role> getUserRole(HttpServletRequest request) {
        Integer UserId = (Integer) request.getAttribute("userId");
        if (UserId == null || UserId == -1) {
            return List.of(Role.guest());
        }
        return rbacService.getRolesByUserId(UserId);
    }

}

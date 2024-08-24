package top.suyiiyii.sims.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.MpUserMapper;
import top.suyiiyii.sims.utils.JwtUtils;

/**
 * @Author tortoise
 * @Date 2024/8/12 11:33
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: JwtInterceptor
 * @Description: TODO
 * @Version 1.0
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("/error".equals(request.getRequestURI())) {
            return true;
        }
        // 从 Authorization 头中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // 去除 "Bearer " 前缀
            token = token.substring(7);
        } else {
            // 如果没有有效的token，设置userId为-1，表示未登录
            request.setAttribute("userId", -1);
            return true;
        }
        // 验证 token 的有效性
        if (!JwtUtils.verifyToken(token, secret)) {
            throw new ServiceException("401", "登录已过期，请重新登录");
        }

        // 获取 token 中的 user id
        String userId = JwtUtils.extractUserId(token);

        request.setAttribute("userId", userId);
        return true;
    }
}

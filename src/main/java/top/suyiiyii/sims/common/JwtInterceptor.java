package top.suyiiyii.sims.common;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.suyiiyii.sims.entity.User;
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

    @Autowired
    MpUserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从 Authorization 头中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // 去除 "Bearer " 前缀
        } else {
            // 如果 Authorization 头中没有 token，则尝试从请求参数中获取
            token = request.getParameter("token");
        }
        // 如果不是映射到方法直接通过
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                return true;
            }
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            //权限错误
            throw new ServiceException("401", "请登录");
        }
        // 获取 token 中的 user id
        String userId= JwtUtils.extractUserId(token);
        if (userId == null) {
            throw new ServiceException("401", "请登录");
        }

        User user = userMapper.selectById(Integer.parseInt(userId));
        if (user == null) {
            throw new ServiceException("401", "请登录");
        }
        // 验证 token 的有效性
        if (!JwtUtils.verifyToken(token, user.getPassword())) {
            throw new ServiceException("401", "请登录");
        }
        // 验证token后，如果一切正常，将token存储到request的属性中
        request.setAttribute("token", token);
        return true;
    }
}

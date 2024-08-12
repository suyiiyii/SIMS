package top.suyiiyii.sims.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.exception.ServiceException;
import top.suyiiyii.sims.mapper.UserMapper;

/**
 * @Author tortoise
 * @Date 2024/8/12 11:33
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: JwtInterceptor
 * @Description: TODO
 * @Version 1.0
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从 Authorization 头中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // 去除 "Bearer " 前缀
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
            throw new ServiceException("401", "请登录");//权限错误
        }

        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "请登录");
        }

        User user = userMapper.selectByUserId(Integer.parseInt(userId));
        if (user == null) {
            throw new ServiceException("401", "请登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();//加密,认证
//jwtVerifier  验证器
        try {
            jwtVerifier.verify(token);
        } catch (JWTDecodeException e) {

            throw new ServiceException("401", "请登录");
        }
        return true;
    }
}

package top.suyiiyii.sims.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.suyiiyii.sims.entity.User;
import top.suyiiyii.sims.mapper.UserMapper;

import java.util.Date;

/**
 * @Author tortoise
 * @Date 2024/8/12 11:44
 * @PackageName:top.suyiiyii.sims.utils
 * @ClassName: JwtUtils
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class JwtUtils {
    private static UserMapper staticUserMapper;
    @Resource
    UserMapper userMapper;

    /**
     * @param userId
     * @param sign
     * @author: tortoise
     * @date: 2024/8/1 15:12
     * @Description: 生成token
     * @return: java.lang.String
     */
    public static String createToken(String userId, String sign) {
        return JWT.create()
                .withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(sign));
        // 设置令牌过期时间为2小时
    }

    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // 验证 JWT 令牌
    public static boolean verifyToken(String token, String secret) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build(); // 创建 JWT 验证器
            jwtVerifier.verify(token); // 验证令牌
            return true;
        } catch (JWTDecodeException e) {
            // 处理异常或记录日志
            return false;
        }
    }

    public static String extractUserId(String token) {
        try {
            return JWT.decode(token).getAudience().get(0); // 从 token 中提取用户ID
        } catch (JWTDecodeException e) {
            // 处理异常或记录日志
            return null;
        }
    }

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }
}

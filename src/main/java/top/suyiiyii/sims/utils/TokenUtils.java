package top.suyiiyii.sims.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
 * @ClassName: TokenUtils
 * @Description: TODO
 * @Version 1.0
 */
@Component
public class TokenUtils{
    private static UserMapper staticUserMapper;
    @Resource
    UserMapper userMapper;
    @PostConstruct
    public void setUserService() {
        staticUserMapper=userMapper;
    }

    /**
     * @author: tortoise
     * @date: 2024/8/1 15:12
     * @Description: 生成token
     * @param userId
     * @param sign
     * @return: java.lang.String
     */
    public static String createToken(String userId, String sign) {
        return JWT.create().withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(sign));

    }
    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {

                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}

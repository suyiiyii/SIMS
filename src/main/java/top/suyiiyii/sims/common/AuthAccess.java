package top.suyiiyii.sims.common;

import java.lang.annotation.*;

/**
 * @Author tortoise
 * @Date 2024/8/12 11:26
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: AuthAccess
 * @Description: TODO
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
    String[] allowRoles() default {};
}

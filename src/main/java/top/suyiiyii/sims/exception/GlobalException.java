package top.suyiiyii.sims.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.suyiiyii.sims.common.Result;

/**
 * @Author tortoise
 * @Date 2024/8/11 1:38
 * @PackageName:top.suyiiyii.sims.exception
 * @ClassName: GlobalException
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result ServiceException(ServiceException e) {
        log.warn("ServiceException:{}", e.getMessage());
        // 打印错误调用栈
        log.warn("ServiceException:", e);
        return Result.error(e.getCode(), e.getMessage());
    }
}

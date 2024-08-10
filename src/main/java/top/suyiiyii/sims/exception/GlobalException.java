package top.suyiiyii.sims.exception;

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
@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result ServiceException(ServiceException e){
        return Result.error(e.getCode(),e.getMessage());
    }
}

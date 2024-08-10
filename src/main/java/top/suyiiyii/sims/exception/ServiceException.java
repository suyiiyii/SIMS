package top.suyiiyii.sims.exception;

import lombok.Getter;

/**
 * @Author tortoise
 * @Date 2024/8/11 1:38
 * @PackageName:top.suyiiyii.sims.exception
 * @ClassName: ServiceException
 * @Description: TODO
 * @Version 1.0
 */
@Getter
public class ServiceException extends RuntimeException{
    public final String code;

    public ServiceException(String msg){
        super(msg);
        this.code = "500";
    }
    public ServiceException(String code ,String msg){
        super(msg);
        this.code = code;
    }
}

package top.suyiiyii.sims.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author tortoise
 * @Date 2024/8/10 21:18
 * @PackageName:top.suyiiyii.sims.common
 * @ClassName: Result
 * @Description: TODO
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    public static final String CODE_SUCCESS="200";
    public static final String CODE_AUTH_ERROR="401";
    public static final String CODE_SYS_ERROR="500";
    private String code;

    private String msg;
    private Object data;
    public static Result success(){
        return new Result(CODE_SUCCESS,"success",null);
    }
    public static Result success(Object data){
        return new Result(CODE_SUCCESS,"success",data);
    }
    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    public static Result authError(String msg){
        return new Result(CODE_AUTH_ERROR,"系统错误",null);
    }
}

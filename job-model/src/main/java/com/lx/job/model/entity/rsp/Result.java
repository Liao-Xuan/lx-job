package com.lx.job.model.entity.rsp;

import com.lx.job.model.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;


/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:28
 * @Version: 1.0
 */

@Getter
@ApiModel(value = "返回结果")
public class Result<T> {

    @ApiModelProperty("返回code")
    private Integer code;
    @ApiModelProperty("返回信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    public Result() {
        this.code = ErrorEnum.SUCCESS.getCode();
        this.message = ErrorEnum.SUCCESS.getMessage();
    }

    private Result(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> error(String message) {
        return new Result<T>(ErrorEnum.SYSTEM_ERROR.getCode(), message);
    }

    public static <T> Result<T> error(ErrorEnum err) {
        return new Result<T>(err.getCode(), err.getMessage());
    }

    public static <T> Result<T> error(Integer errCode, String message) {
        return new Result<T>(errCode, message);
    }

    public static <T> Result<T> success() {
        return new Result<T>();
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMessage(), data);
    }
}

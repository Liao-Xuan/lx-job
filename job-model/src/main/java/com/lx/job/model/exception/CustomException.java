package com.lx.job.model.exception;

import com.lx.job.model.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:28
 * @Version: 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public CustomException(String message) {
        super(message);
        this.code = ErrorEnum.SYSTEM_ERROR.getCode();
        this.message = message;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
        this.code = ErrorEnum.SYSTEM_ERROR.getCode();
        this.message = message;
    }
}

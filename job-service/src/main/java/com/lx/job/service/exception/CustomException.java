package com.lx.job.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:06
 * @Version: 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = -4870885332490778170L;

    private Integer code;
    private String message;

    public CustomException(ErrorEnum eEnum) {
        super(eEnum.getMessage());
        this.code = eEnum.getCode();
        this.message = eEnum.getMessage();
    }

    public CustomException(String message) {
        super(message);
        this.code = ErrorEnum.SYSTEM_ERR.getCode();
        this.message = message;
    }

    public CustomException(String message,Throwable throwable) {
        super(message,throwable);
        this.code = ErrorEnum.SYSTEM_ERR.getCode();
        this.message = message;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}

package com.lx.job.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/12/18 13:58
 * @Version: 1.0
 */

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    SUCCESS(200, "OK"),
    SYSTEM_ERROR(500, "system error"),
    ;

    private Integer code;
    private String message;
}

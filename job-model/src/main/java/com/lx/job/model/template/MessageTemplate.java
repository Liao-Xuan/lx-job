package com.lx.job.model.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 消息模板
 * @Author: liaoxuan
 * @Date: 2020/11/6 22:01
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageTemplate<T> implements Serializable {
    private static final long serialVersionUID = 1039417210335977275L;

    /**
     * 消息体
     */
    private T message;
}

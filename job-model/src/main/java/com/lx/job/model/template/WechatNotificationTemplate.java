package com.lx.job.model.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:34
 * @Version: 1.0
 */

@EqualsAndHashCode
@Data
public class WechatNotificationTemplate<T> implements Serializable {
    private static final long serialVersionUID = 7822794630983023908L;

    /**
     * 消息模板ID
     */
    private String templateId;
    /**
     * 小程序跳转路径
     */
    private String page;
    /**
     * 接收者（用户）的 openid
     */
    private String toUser;
    /**
     * 消息内容
     */
    private T data;
}

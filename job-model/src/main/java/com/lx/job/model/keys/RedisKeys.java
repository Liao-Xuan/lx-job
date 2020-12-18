package com.lx.job.model.keys;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:06
 * @Version: 1.0
 */

public interface RedisKeys {

    String BOX_NOTIFICATION = "box::notification::";

    /**
     * 微信token
     */
    String WECHAT_ACCESS_TOKEN = BOX_NOTIFICATION + "wechat_access_token";
}

package com.lx.job.service.client;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lx.job.model.keys.CommonKeys;
import com.lx.job.model.keys.RedisKeys;
import com.lx.job.model.template.WechatNotificationTemplate;
import com.lx.job.service.exception.CustomException;
import com.lx.job.service.utils.RedisUtilTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 16:59
 * @Version: 1.0
 */

@Service
@Slf4j
public class WechatClient {

    @Resource
    private RedisUtilTemplate redisUtilTemplate;

    @Value("${wechat.subscribeMsg-send.api}")
    private String wechatSubscribeMsgSendApi;

    @Value("${wechat.access-token.api}")
    private String wechatAccessTokenApi;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.secret}")
    private String secret;

    @Value("${wechat.message.miniprogram_state}")
    private String miniProgramState;

    private final String ERROR_MSG = "errmsg";
    private final String ERROR_CODE = "errcode";
    private final Integer CODE = 0;
    private final Integer ACCESS_TOKEN_ERROR_CODE1 = 42001;
    private final Integer ACCESS_TOKEN_ERROR_CODE2 = 40001;
    private final Integer USER_REJECTED_ERROR_CODE = 43101;

    /**
     * 获取微信 accessToken
     *
     * @return
     */
    private String getAccessToken() {
        String accessToken = (String) redisUtilTemplate.get(RedisKeys.WECHAT_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
            try {
                String result = HttpUtil.get(wechatAccessTokenApi + "appid=" + appId + "&secret=" + secret, 3000);
                if (StringUtils.isNotBlank(result)) {
                    JSONObject jsonObject = verifyRequestIsSuccessful(result, null);
                    accessToken = jsonObject.getString("access_token");
                    long time = jsonObject.getLong("expires_in");
                    redisUtilTemplate.set(RedisKeys.WECHAT_ACCESS_TOKEN, accessToken, time);
                }
            } catch (Exception e) {
                log.error("获取微信access_token失败 {}", e.getMessage(), e);
                throw new CustomException("获取微信access_token失败");
            }
        }
        return accessToken;
    }

    /**
     * 验证请求是否成功
     *
     * @param result
     * @return
     */
    private JSONObject verifyRequestIsSuccessful(String result, String openid) {
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (Objects.nonNull(jsonObject.getInteger(ERROR_CODE))) {
            if (jsonObject.getInteger(ERROR_CODE).equals(ACCESS_TOKEN_ERROR_CODE1) || jsonObject.getInteger(ERROR_CODE).equals(ACCESS_TOKEN_ERROR_CODE2)) {
                redisUtilTemplate.del(RedisKeys.WECHAT_ACCESS_TOKEN);
                throw new CustomException("access_token失效");
            }
            if (jsonObject.getInteger(ERROR_CODE).equals(USER_REJECTED_ERROR_CODE)) {
                log.error("用户拒绝接受消息 openid:{}", openid);
            }
        }
        return jsonObject;
    }

    /**
     * 微信发送通知消息
     *
     * @param req
     * @return
     */
    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(value = 1000, multiplier = 1))
    public boolean sendWechatMsg(WechatNotificationTemplate<?> req) {
        //接口调用凭证
        String accessToken = this.getAccessToken();
        JSONObject param = new JSONObject();
        param.fluentPut("touser", req.getToUser());
        param.fluentPut("template_id", req.getTemplateId());
        param.fluentPut("page", req.getPage());
        param.fluentPut("data", JSON.parseObject(JSONObject.toJSONString(req.getData())));
        param.fluentPut("miniprogram_state", miniProgramState);
        try {
            String result = HttpUtil.post(wechatSubscribeMsgSendApi + accessToken, param.toJSONString(), 3000);
            log.info("sendWechatMsg result -> {}", result);
            JSONObject resultJson = null;
            if (StringUtils.isNotBlank(result)) {
                resultJson = this.verifyRequestIsSuccessful(result, req.getToUser());
            }
            if (Objects.nonNull(resultJson) && resultJson.getInteger(ERROR_CODE).equals(CODE) && StringUtils.equals(resultJson.getString(ERROR_MSG), CommonKeys.OK)) {
                log.info("发送微信通知消息成功 openid:{} data:{}", req.getToUser(), req.getData());
                return true;
            }
        } catch (Exception e) {
            log.error("发送微信通知消息失败 req {} errorMsg:{}", req, e.getMessage(), e);
            throw new CustomException("发送微信通知消息失败", e);
        }
        return false;
    }

}

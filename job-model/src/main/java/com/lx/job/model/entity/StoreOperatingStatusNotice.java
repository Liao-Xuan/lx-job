package com.lx.job.model.entity;

import com.lx.job.model.template.TemplateValue;
import com.lx.job.model.template.WechatNotificationTemplate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:28
 * @Version: 1.0
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class StoreOperatingStatusNotice extends WechatNotificationTemplate<StoreOperatingStatusNotice> implements Serializable {
    private static final long serialVersionUID = 5132819987371511297L;

    /**
     * 店铺名称
     */
    private TemplateValue thing1;
    /**
     * 店铺地址
     */
    private TemplateValue thing2;
    /**
     * 营业状态
     */
    private TemplateValue phrase3;
    /**
     * 温馨提示
     */
    private TemplateValue thing4;
}

package com.lx.job.model.template;

import lombok.Builder;
import lombok.Data;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/6/12 17:30
 * @Version: 1.0
 */
@Data
@Builder
public class TemplateValue {
    /**
     * 模板 key
     */
    private String value;
}

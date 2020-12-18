package com.lx.job.service.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/9 13:58
 * @Version: 1.0
 */

@UtilityClass
public class OrderNoUtils {

    /**
     * 生成订单编号
     *
     * @return
     */
    public static String generateOrderNo() {
        return StrUtil.fillAfter(System.nanoTime() + RandomUtil.randomInt(0, 100) + "", RandomUtil.randomChar(RandomUtil.BASE_NUMBER), 15);
    }

    /**
     * 生成交易单号
     *
     * @return
     */
    public static String generateTxNo() {
        return "tx" + StrUtil.fillAfter(System.nanoTime() + RandomUtil.randomInt(0, 100) + "", RandomUtil.randomChar(RandomUtil.BASE_NUMBER), 15);
    }
}

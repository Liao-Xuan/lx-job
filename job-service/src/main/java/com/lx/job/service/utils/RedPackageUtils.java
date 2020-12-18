package com.lx.job.service.utils;


import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/6 9:20
 * @Version: 1.0
 */

@Component
@Slf4j
public class RedPackageUtils {

    @Resource
    private RedisUtilTemplate redisUtilTemplate;

    @Data
    public static class RedPackage {
        /**
         * 剩余的红包数量
         */
        private int remainSize;
        /**
         * 剩余的钱
         */
        private BigDecimal remainMoney;
        /**
         * 最小金额
         */
        private BigDecimal minMoney;
        /**
         * 最大金额
         */
        private BigDecimal maxMoney;
        /**
         * 活动ID
         */
        private Long activityId;
        /**
         * 学校ID
         */
        private Long uniId;
    }

    /**
     * @param rp
     * @return
     */
    private BigDecimal getRandomMoney(RedPackage rp) {
        if (rp.remainSize == 1) {
            rp.remainSize--;
            BigDecimal m = rp.remainMoney.setScale(2, BigDecimal.ROUND_DOWN);
            if (m.compareTo(rp.getMaxMoney()) > 0) {
                return rp.getMaxMoney().setScale(2, BigDecimal.ROUND_DOWN);
            }
            return m;
        }

        BigDecimal random = BigDecimal.valueOf(Math.random());
        BigDecimal halfRemainSize = BigDecimal.valueOf(rp.remainSize).divide(new BigDecimal(2), BigDecimal.ROUND_UP);
        BigDecimal max1 = rp.remainMoney.divide(halfRemainSize, BigDecimal.ROUND_DOWN);
        BigDecimal minRemainAmount = rp.getMinMoney().multiply(BigDecimal.valueOf(rp.remainSize - 1)).setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal max2 = rp.remainMoney.subtract(minRemainAmount);
        BigDecimal max = (max1.compareTo(max2) < 0) ? max1 : max2;

        BigDecimal money = random.multiply(max).setScale(2, BigDecimal.ROUND_DOWN);
        money = money.compareTo(rp.getMinMoney()) < 0 ? rp.getMinMoney() : money;

        rp.remainSize--;
        rp.remainMoney = rp.remainMoney.subtract(money).setScale(2, BigDecimal.ROUND_DOWN);
        if (money.compareTo(rp.getMaxMoney()) > 0) {
            return rp.getMaxMoney().setScale(2, BigDecimal.ROUND_DOWN);
        }
        if (money.compareTo(BigDecimal.ZERO) <= 0) {
            return rp.getMinMoney().setScale(0, BigDecimal.ROUND_DOWN);
        }
        return money;
    }

    /**
     * 发送红包
     *
     * @param rp
     * @return
     */
    private List<BigDecimal> sendRedPackage(RedPackage rp) {
        log.info("本次发送红包数量：{} 红包总金额：{} 最大金额：{} 最小金额：{}", rp.remainSize, rp.getRemainMoney(), rp.getMaxMoney(), rp.minMoney);
        List<BigDecimal> moneyList = Lists.newArrayList();
        while (rp.remainSize != 0) {
            BigDecimal randomMoney = getRandomMoney(rp);
            moneyList.add(randomMoney);
        }
        log.info("红包详情：{}", moneyList);
        BigDecimal m = moneyList.stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
        log.info("最后发送总金额：{}", m);
        return moneyList;
    }

}

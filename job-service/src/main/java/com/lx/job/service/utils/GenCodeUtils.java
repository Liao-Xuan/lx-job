package com.lx.job.service.utils;

import lombok.experimental.UtilityClass;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/12/3 15:10
 * @Version: 1.0
 */

@UtilityClass
public class GenCodeUtils {

    private static final String CODES = "1234567890";

    /**
     * 获取随机码
     *
     * @param len
     * @return
     */
    public static String getRandomCode(int len) {
        StringBuilder linkNo = new StringBuilder();
        char[] m = CODES.toCharArray();
        for (int j = 0; j < len; j++) {
            char c = m[(int) (Math.random() * 10)];
            // 保证六位随机数之间没有重复的
            if (linkNo.toString().contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo.append(c);
        }
        return linkNo.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(getRandomCode(4));
        }
    }
}

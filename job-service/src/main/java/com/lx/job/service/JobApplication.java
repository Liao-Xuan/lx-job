package com.lx.job.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:34
 * @Version: 1.0
 */

@SpringBootApplication
@EnableRetry
@EnableCaching
@EnableScheduling
public class JobApplication {

    public static ConfigurableApplicationContext cxt = null;

    public static void main(String[] args) {
        cxt = SpringApplication.run(JobApplication.class, args);
    }
}

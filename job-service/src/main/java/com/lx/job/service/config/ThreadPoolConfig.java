package com.lx.job.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池配置
 * @Author: liaoxuan
 * @Date: 2020/3/20 17:16
 * @Version: 1.0
 */
@Component
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Value("${task.pool.queueCapacity}")
    private int queueCapacity;
    @Value("${task.pool.keepAliveSeconds}")
    private int keepAliveSeconds;
    /**
     * 获取 CPU 核心数量
     */
    private final int coreSize = Runtime.getRuntime().availableProcessors() + 1;

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(coreSize);
        // 最大线程数
        executor.setMaxPoolSize(coreSize + 10);
        // 队列容量
        executor.setQueueCapacity(queueCapacity);
        // 活跃时间
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程名字前缀
        executor.setThreadNamePrefix("lx-job-taskExecutor-");
        // 设置拒绝策略
        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 执行初始化
        executor.initialize();
        return executor;
    }

}

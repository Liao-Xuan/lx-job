package com.lx.job.service.redisson;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/6/19 16:51
 * @Version: 1.0
 */

public interface RedissonLockService {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long timeout);

    RLock lock(String lockKey, TimeUnit unit, long timeout);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}

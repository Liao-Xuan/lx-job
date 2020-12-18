package com.lx.job.service.aspect;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 17:28
 * @Version: 1.0
 */

@Component
@Aspect
@Slf4j
public class ControllerVerifyAspect {

    @Value("${dynamic.request.debug}")
    private boolean enable;

    @Pointcut("within(com.lx.job.service.controller..*)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (enable) {
            String methodName = "@请求日志[" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "]";
            String requestStr = getRequestParam(joinPoint);
            log.info("{} 请求参数：{}", methodName, requestStr);
            long startTime = System.currentTimeMillis();
            Object result = null;
            try {
                result = joinPoint.proceed();
            } catch (Exception e) {
                //如果有异常继续抛
                throw e;
            } finally {
                long handleTime = System.currentTimeMillis() - startTime;
                String responseStr = Objects.isNull(result) ? "无" : toJson(result);
                log.info("{} 输出结果：{} -> 耗时:{}", methodName, responseStr, handleTime);
            }
            return result;
        } else {
            return joinPoint.proceed();
        }
    }

    public static String toJson(Object... result) {
        if (Objects.isNull(result)) {
            return "无返回结果";
        }
        List<Object> results = Lists.newArrayList();
        try {
            for (Object o : result) {
                if (!(o instanceof ServletResponse)) {
                    results.add(o);
                }
            }
            return JSON.toJSONString(results);
        } catch (Exception e) {
            log.warn("序列化失败 result:{} error:{}", results.toString(), e.getMessage());
            return "序列化失败:" + results.toString();
        }
    }

    /**
     * 获取请求参数
     *
     * @param point
     * @return
     */
    private String getRequestParam(ProceedingJoinPoint point) {
        Object[] methodArgs = point.getArgs();
        Parameter[] parameters = ((MethodSignature) point.getSignature()).getMethod().getParameters();
        String req;
        try {
            req = logParam(parameters, methodArgs);
        } catch (Exception e) {
            log.error("解析请求参数失败", e);
            req = "获取参数失败";
        }
        return req;
    }

    /**
     * 拼接请求参数
     *
     * @param paramsArgsName
     * @param paramsArgsValue
     * @return
     */
    private String logParam(Parameter[] paramsArgsName, Object[] paramsArgsValue) {
        if (Objects.isNull(paramsArgsName) || Objects.isNull(paramsArgsValue)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < paramsArgsValue.length; i++) {
            //参数名
            String name = paramsArgsName[i].getName();
            //参数值
            Object value = paramsArgsValue[i];
            builder.append(name).append("=");
            if (value instanceof String) {
                builder.append(value).append(",");
            } else {
                if (value instanceof ServletRequest) {
                    builder.append("ServletRequest");
                } else {
                    builder.append(JSON.toJSONString(value)).append(",");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

}

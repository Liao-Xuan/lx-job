package com.lx.job.test;

import com.lx.job.model.constants.RabbitConstants;
import com.lx.job.model.template.MessageTemplate;
import com.lx.job.service.JobApplication;
import com.lx.job.service.rabbitmq.provider.MessageProvider;
import com.lx.job.service.utils.RedisUtilTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/14 18:50
 * @Version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplication.class)
public class JobTest {

    @Resource
    private MessageProvider messageProvider;

    @Resource
    private RedisUtilTemplate redisUtilTemplate;

    @Test
    public void testRabbitMq() {
        MessageTemplate<String> template = new MessageTemplate<>();
        template.setMessage("hello lx job");
        messageProvider.sendRabbitMqMessage(template, RabbitConstants.JOB_QUEUE);

        redisUtilTemplate.set("key",template);

        System.out.println(redisUtilTemplate.get("key"));
    }
}

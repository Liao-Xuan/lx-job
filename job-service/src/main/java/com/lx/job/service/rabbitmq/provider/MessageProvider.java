package com.lx.job.service.rabbitmq.provider;

import com.lx.job.model.exception.CustomException;
import com.lx.job.model.template.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: liaoxuan
 * @Date: 2020/11/6 22:35
 * @Version: 1.0
 */

@Component
@Slf4j
public class MessageProvider {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendRabbitMqMessage(MessageTemplate<?> message, String queue) {
        try {
            rabbitTemplate.convertAndSend(queue, message);
        } catch (AmqpException e) {
            log.error("MQ消息发送失败 message:{}", message, e);
            throw new CustomException("MQ消息发送失败");
        }
    }
}

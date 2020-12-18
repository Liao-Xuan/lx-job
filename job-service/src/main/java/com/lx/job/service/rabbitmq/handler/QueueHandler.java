package com.lx.job.service.rabbitmq.handler;

import com.alibaba.fastjson.JSON;
import com.lx.job.model.constants.RabbitConstants;
import com.lx.job.model.template.MessageTemplate;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:消息处理器
 * @Author: liaoxuan
 * @Date: 2020/11/6 22:01
 * @Version: 1.0
 */

@Slf4j
@Component
public class QueueHandler {

    @RabbitHandler
    @RabbitListener(queues = RabbitConstants.JOB_QUEUE)
    public void couponMessageHandlerManualAck(MessageTemplate<?> template, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("MQ接收到保存优惠券消息 msg：{}", JSON.toJSONString(template));

            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                channel.basicRecover();
                log.info("消息处理失败,重新压入MQ");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}

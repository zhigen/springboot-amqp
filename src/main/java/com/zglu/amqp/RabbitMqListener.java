package com.zglu.amqp;

import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zglu
 */
@Log4j2
@Component
public class RabbitMqListener {

    private static final String ERROR_MSG = "消息消费失败:";

    @RabbitListener(queues = QueueConstant.DIRECTQUEUE)
    public void direct(String content, Channel channel, Message message) throws IOException {
        try {
            log.info(QueueConstant.DIRECTQUEUE.concat(":").concat(content));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(ERROR_MSG.concat(QueueConstant.DIRECTQUEUE), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = QueueConstant.FANOUTQUEUE0)
    public void fanout0(String content, Channel channel, Message message) throws IOException {
        try {
            log.info(QueueConstant.FANOUTQUEUE0.concat(":").concat(content));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(ERROR_MSG.concat(QueueConstant.FANOUTQUEUE0), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = QueueConstant.FANOUTQUEUE1)
    public void fanout1(String content, Channel channel, Message message) throws IOException {
        try {
            log.info(QueueConstant.FANOUTQUEUE1.concat(":").concat(content));
            throw new NullPointerException();
        } catch (Exception e) {
            log.error(ERROR_MSG.concat(QueueConstant.FANOUTQUEUE1), e);
            // 拒绝重回原队列，如队列配置了死信队列，则进入死信队列
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = QueueConstant.TOPICQUEUE0)
    public void topic0(String content, Channel channel, Message message) throws IOException {
        try {
            log.info(QueueConstant.TOPICQUEUE0.concat(":").concat(content));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(ERROR_MSG.concat(QueueConstant.TOPICQUEUE0), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = QueueConstant.TOPICQUEUE1)
    public void topic1(String content, Channel channel, Message message) throws IOException {
        try {
            log.info(QueueConstant.TOPICQUEUE1.concat(":").concat(content));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error(ERROR_MSG.concat(QueueConstant.TOPICQUEUE1), e);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}

package com.zglu.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zglu
 */
@Log4j2
@Component
@AllArgsConstructor
public class RabbitMqBean {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param routingKey 路由
     * @param body       消息体
     */
    public void send(String exchange, String routingKey, String body) {
        rabbitTemplate.convertAndSend(exchange, routingKey, body);
    }

    @Bean
    public Queue dlxQueue() {
        return new Queue(QueueConstant.DLXQUEUE);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(QueueConstant.DLXEXCHANGE);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with(QueueConstant.DLXQUEUE);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(QueueConstant.DIRECTQUEUE);
    }

    @Bean
    public Queue fanoutQueue0() {
        return new Queue(QueueConstant.FANOUTQUEUE0);
    }

    @Bean
    public Queue fanoutQueue1() {
        Map<String, Object> arguments = new HashMap<>(2);
        // 绑定该队列到私信交换机
        arguments.put("x-dead-letter-routing-key", QueueConstant.DLXQUEUE);
        arguments.put("x-dead-letter-exchange", QueueConstant.DLXEXCHANGE);
        return new Queue(QueueConstant.FANOUTQUEUE1, true, false, false, arguments);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(QueueConstant.FANOUTEXCHANGE);
    }

    @Bean
    public Binding fanoutBinding0() {
        return BindingBuilder.bind(fanoutQueue0()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Queue topicQueue0() {
        return new Queue(QueueConstant.TOPICQUEUE0);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(QueueConstant.TOPICQUEUE1);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(QueueConstant.TOPICEXCHANGE);
    }

    @Bean
    public Binding topicBinding0() {
        return BindingBuilder.bind(topicQueue0()).to(topicExchange()).with(QueueConstant.TOPICQUEUE0);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(QueueConstant.TOPICQUEUE1);
    }
}

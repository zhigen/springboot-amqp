package com.zglu.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
    public Queue directQueue() {
        return new Queue(QueueConstant.DIRECTQUEUE, false);
    }

    @Bean
    public Queue fanoutQueue0() {
        return new Queue(QueueConstant.FANOUTQUEUE0, false);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(QueueConstant.FANOUTQUEUE1, false);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(QueueConstant.FANOUTEXCHANGE, false, false);
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
        return new Queue(QueueConstant.TOPICQUEUE0, false);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(QueueConstant.TOPICQUEUE1, false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(QueueConstant.TOPICEXCHANGE, false, false);
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

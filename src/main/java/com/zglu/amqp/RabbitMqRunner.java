package com.zglu.amqp;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zglu
 */
@Log4j2
@EnableRabbit
@Configuration
@AllArgsConstructor
public class RabbitMqRunner implements ApplicationRunner, RabbitTemplate.ReturnCallback {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(ApplicationArguments args) {
        // 设置返回回调
        rabbitTemplate.setReturnCallback(this);
        // 设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("correlationData", correlationData);
                map.put("cause", cause);
                log.error("消息确认失败:".concat(map.toString()));
            }
        });
    }

    @Override
    public void returnedMessage(org.springframework.amqp.core.Message message, int i, String s, String s1, String s2) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("message", message);
        map.put("i", i);
        map.put("s", s);
        map.put("s1", s1);
        map.put("s2", s2);
        log.error("消息退回:".concat(map.toString()));
    }
}

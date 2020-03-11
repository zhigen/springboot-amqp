package com.zglu.amqp;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zglu
 */
@RestController
@AllArgsConstructor
public class RabbitMqController {

    private final RabbitMqBean rabbitMqBean;

    @GetMapping("/direct")
    @ApiOperation("direct，直接模式，消息直接转发到routingKey指定的queue，发送时无需指定交换机，queue无需绑定交换机，无对应queue抛弃")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "routingKey", value = "路由键", defaultValue = QueueConstant.DIRECTQUEUE),
    })
    public void direct(String routingKey) {
        rabbitMqBean.send(null, routingKey, "body");
    }

    @GetMapping("/fanout")
    @ApiOperation("fanout，订阅模式，消息发送到交换机，由交换机转发到绑定的queue，发送时无需指定routingKey，exchange未绑定queue抛弃")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "exchange", value = "交换器", defaultValue = QueueConstant.FANOUTEXCHANGE),
    })
    public void fanout(String exchange) {
        rabbitMqBean.send(exchange, null, "body");
    }

    @GetMapping("/topic")
    @ApiOperation("topic，主题模式，消息发送到交换机，由交换机按routingKey规则转发到绑定的符合规则的queue，发送时需要指定routingKey，exchange无匹配queue抛弃")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "exchange", value = "交换器", defaultValue = QueueConstant.TOPICEXCHANGE),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "routingKey", value = "路由键", defaultValue = QueueConstant.TOPICQUEUE0),
    })
    public void topic(String exchange, String routingKey) {
        rabbitMqBean.send(exchange, routingKey, "body");
    }
}

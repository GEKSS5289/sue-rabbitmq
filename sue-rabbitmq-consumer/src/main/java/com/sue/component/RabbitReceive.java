package com.sue.component;

import com.rabbitmq.client.Channel;
import com.sue.JsonUtils;
import com.sue.Users;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author sue
 * @date 2020/8/15 14:17
 */

@Component
public class RabbitReceive {

    /**
     * @RabbitListener @QueueBinding @Queue @Exchange
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(
                    name = "exchange-1",
                    durable = "true",
                    type="topic",
                    ignoreDeclarationExceptions = "true"
            ),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{
        //消费消息
        System.out.println("----------------");
        System.out.println("消费消息:"+ JsonUtils.jsonToPojo(message.getPayload().toString(),Users.class).getName());
        //处理成功后 获取deliverTag 并进行手工ACK操作，因为我们配置文件里配置的是手工签收
        Long deliverTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliverTag,false);
    }
}

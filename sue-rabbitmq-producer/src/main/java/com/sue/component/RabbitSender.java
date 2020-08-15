package com.sue.component;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author sue
 * @date 2020/8/15 13:38
 */


@Component
public class RabbitSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 这里就是确认消息回调监听接口，用于确认消息是否被broker所收到
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){
        /**
         *
         * @param correlationData 作为一个唯一的标识
         * @param ack broker是否落盘成功 true:成功 false:失败
         * @param cause 失败的异常信息
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        }
    };

    /**
     *  对外发送的消息方法
     * @param message 具体的消息内容
     * @param properties 额外的附加属性
     * @throws Exception
     */
    public void send(Object message, Map<String,Object> properties) throws Exception{
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<?> msg = MessageBuilder.createMessage(message, mhs);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend(
                "exchange-1",
                "springboot.rabbit",
                msg,
                message1 -> {
                    System.out.println(message1);
                    return message1;
                },
                correlationData);


    }
}

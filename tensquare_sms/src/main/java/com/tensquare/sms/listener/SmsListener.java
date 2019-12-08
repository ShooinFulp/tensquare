package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/8 10:50 下午
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitHandler
    public void sendSms(Map<String, String> message) {
        System.out.println("手机号" + message.get("mobile"));
        System.out.println("验证码" + message.get("code"));
    }
}

package com.tensquare.rabbitmq.custmer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/8 8:22 下午
 */
@Component
@RabbitListener(queues = "csy")
public class Custmer2 {
    @RabbitHandler
    public void  showMsg(String msg){
        System.out.println(msg);
    }
}

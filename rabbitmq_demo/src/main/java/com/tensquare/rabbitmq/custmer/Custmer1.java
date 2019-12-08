package com.tensquare.rabbitmq.custmer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈功能概述〉
 *
 * @author: fuliping
 * @date: 2019/12/8 8:19 下午
 */
@Component
@RabbitListener(queues ="fuli" )
public class Custmer1 {

    @RabbitHandler
    public void  showMessage(String meg){
        System.out.println(meg);
    }
}

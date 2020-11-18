package com.acui.amqp;

import com.acui.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
//        amqpAdmin.declareExchange(new DirectExchange(("amqpadmin.direction")));
//        System.out.println("创建完成");
//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
        //创建绑定规则
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.direction", "amqp.haha", null));
    }

    /**
     * direct
     */
    @Test
    void contextLoads() {
//        Message need to customize header and body
//        rabbitTemplate.send(exchange,routekey,message);
        //only need object, auto seriliazed and send to  rabbitmq
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "This is the first message");
        map.put("data", Arrays.asList("helloworld", 123, true));
        //serilize by default
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("xiyouji", "wuchengen"));
    }
    //recieve data, how to make it to json
    @Test
    public void recieve() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("hongloumeng","wudchengen"));
    }
}

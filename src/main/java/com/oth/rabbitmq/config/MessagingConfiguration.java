package com.oth.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfiguration {

    // QUEUES
    @Bean
    public Queue queue1(){
        return new Queue(Util.QUEUES.get(0));
    }
    @Bean
    public Queue queue2(){
        return new Queue(Util.QUEUES.get(1));
    }

    // -- topic exchange exemple
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(Util.EXCHANEGES.get(0));
    }

    // -- delayed message exchange
    @Bean
    CustomExchange delayedExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(Util.EXCHANEGES.get(1), "x-delayed-message",true, false,args);
    }

    //Binding 1 : between queue1 and topic exchange
    @Bean
    public Binding bindingQueue1AndTopicExchange(){
        return BindingBuilder.bind(queue1()).to(topicExchange()).with(Util.ROUTING_KEYS.get(0));
    }
    //Binding 2 : between queue1 and x-delayed-message
    @Bean
    public Binding bindingQueue2AndDelayedMessageExchange(){
        return BindingBuilder.bind(queue2()).to(delayedExchange()).with(Util.ROUTING_KEYS.get(1)).noargs();
    }


    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;

    }
}

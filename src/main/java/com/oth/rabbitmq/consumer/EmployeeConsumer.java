package com.oth.rabbitmq.consumer;

import com.oth.rabbitmq.entity.EmployeeStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConsumer {

    @RabbitListener(queues = "QUEUE1")
    public void consumerMessageFromQueueWithTopicExchange(EmployeeStatus employeeStatus){
        System.out.println("Message received from queue1 With TopicExchange : " + employeeStatus);
    }
    @RabbitListener(queues = "QUEUE2")
    public void consumerMessageFromQueueWithDelayedMessageExchange(EmployeeStatus employeeStatus){
        System.out.println("Message received from queue2 With DelayedMessageExchange : " + employeeStatus);
    }

}

package com.oth.rabbitmq.publisher;

import com.oth.rabbitmq.config.Util;
import com.oth.rabbitmq.entity.Employee;
import com.oth.rabbitmq.entity.EmployeeStatus;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("employee")
public class EmployeePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("publish")
    public String publishEmployeeTopicExchange(@RequestBody Employee employee){
        employee.setId(UUID.randomUUID().toString());
        EmployeeStatus employeeStatus = new EmployeeStatus(employee, "STARTED", "start process");
        //transfert the employee to service layer ...
        employeeStatus.setStatus("PROGRESS");
        employeeStatus.setMessage("process placed successfully");
        rabbitTemplate.convertAndSend(Util.EXCHANEGES.get(0), Util.ROUTING_KEYS.get(0), employeeStatus);

        return "success with topicExchange!!!";
    }

    @PostMapping("publish2")
    public String publishEmployeeDelayedMessage(@RequestBody Employee employee){
        employee.setId(UUID.randomUUID().toString());
        EmployeeStatus employeeStatus = new EmployeeStatus(employee, "STARTED", "start process");
        //transfert the employee to service layer ...
        employeeStatus.setStatus("PROGRESS");
        employeeStatus.setMessage("process placed successfully");

        rabbitTemplate.convertAndSend(Util.EXCHANEGES.get(1), Util.ROUTING_KEYS.get(1), employeeStatus, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println(message.getMessageProperties());
                message.getMessageProperties().setHeader("x-delay", 10000);
                return message;
            };
        });
        return "success with DelayedMessageExchange!!!";
    }

}
package com.example.demo.config.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListener.class);

    @RabbitListener(queues = { "${rabbitmq.queue.name}" })
    public void listen(String message) {
        LOGGER.info(String.format("Received message -> %s", message));
    }
}

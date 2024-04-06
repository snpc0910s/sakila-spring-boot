package com.example.demo.config.rabbitmq.controller;

import com.example.demo.base.BaseConst;
import com.example.demo.config.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BaseConst.BASE_API + "/rabbit")
public class RabbitCheckController {

    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping(value = "/check-me", produces = {"application/json"})
    public void checkRabbit(@RequestBody String body) {
        rabbitMQSender.sendMessage(body);
    }
}

package com.example.demo.config.ws.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {

//    public List<WebSocketSession> list = new ArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
        System.out.println("Test message {}" +  message.toString());
//        list.add(session);
        session.sendMessage( new TextMessage("Hello world"));
        Thread.sleep(1000);
    }
}

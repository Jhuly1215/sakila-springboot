package com.sakila.reports.websocket;

import com.sakila.reports.entity.SalesByStore;
import com.sakila.reports.repository.SalesByStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
public class ReportWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private SalesByStoreRepository salesRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        List<SalesByStore> sales = salesRepository.findAll();
        session.sendMessage(new TextMessage("Welcome to Sakila Reports. Current Sales Data: " + sales.toString()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        if (payload.equalsIgnoreCase("get-sales")) {
            List<SalesByStore> sales = salesRepository.findAll();
            session.sendMessage(new TextMessage(sales.toString()));
        }
    }
}

package com.orderflow.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.orderflow.model.Trade;

@Component
public class TradePublisher {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void publishTrade(Trade trade) {
        messagingTemplate.convertAndSend("/topic/trades", trade);
    }
}
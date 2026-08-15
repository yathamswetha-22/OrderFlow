package com.orderflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderflow.model.Trade;
import com.orderflow.websocket.TradePublisher;

@Service
public class TradeService {

    private List<Trade> trades = new ArrayList<>();

    @Autowired
    private TradePublisher tradePublisher;

    public void addTrade(Trade trade) {

        trades.add(trade);

        tradePublisher.publishTrade(trade);
    }

    public List<Trade> getAllTrades() {

        return trades;
    }
}
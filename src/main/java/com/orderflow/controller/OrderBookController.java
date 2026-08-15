package com.orderflow.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderflow.engine.OrderBook;

@RestController
public class OrderBookController {

    @Autowired
    private OrderBook orderBook;

    @GetMapping("/orderbook")
    public Map<String, Object> getOrderBook() {

        Map<String, Object> response = new HashMap<>();

        response.put("buyOrders", orderBook.getBuyOrders());
        response.put("sellOrders", orderBook.getSellOrders());

        return response;
    }
}
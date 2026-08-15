package com.orderflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderflow.model.Trade;
import com.orderflow.service.TradeService;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/trades")
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }
}
package com.orderflow.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import com.orderflow.engine.OrderBook;
import com.orderflow.entity.Order;
import com.orderflow.model.OrderBookResponse;
import com.orderflow.model.Trade;
import com.orderflow.service.OrderService;
import com.orderflow.service.TradeService;
import com.orderflow.websocket.TradePublisher;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private OrderBook orderBook;
    @Autowired
    private TradePublisher tradePublisher;

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {

        Trade trade = orderBook.addOrder(order);

        if (trade != null) {

            tradeService.addTrade(trade);

            tradePublisher.publishTrade(trade);

            System.out.println(
                "TRADE EXECUTED -> "
                + trade.getSymbol()
                + " Price: "
                + trade.getPrice()
                + " Quantity: "
                + trade.getQuantity()
            );
        }

        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @GetMapping("/symbol/{symbol}")
    public List<Order> getOrdersBySymbol(@PathVariable String symbol) {
        return orderService.getOrdersBySymbol(symbol);
    }
        @GetMapping("/page")
        public Page<Order> getOrdersWithPagination(
                @RequestParam int page,
                @RequestParam int size) {

            return orderService.getOrdersWithPagination(page, size);
        }
        @GetMapping("/sort")
        public List<Order> getOrdersSorted(
                @RequestParam String field) {

            return orderService.getOrdersSorted(field);
        }
        @GetMapping("/orderbook")
        public OrderBookResponse getOrderBook() {

            return new OrderBookResponse(
                    orderService.getOrderBook().getBuyOrders(),
                    orderService.getOrderBook().getSellOrders()
            );
        }
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id,
            @Valid @RequestBody Order orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
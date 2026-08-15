package com.orderflow.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.orderflow.entity.Order;
import com.orderflow.repository.OrderRepository;
import com.orderflow.exception.ResourceNotFoundException;
import com.orderflow.engine.OrderBook;
import com.orderflow.model.Trade;
@Service
public class OrderService {
	private static final Logger logger =
	        LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TradeService tradeService;
    
    @Autowired
    private OrderBook orderBook;
    public Order createOrder(Order order) {
    	
    	order.setTimestamp(System.currentTimeMillis());

        Order savedOrder = orderRepository.save(order);

        Trade trade = orderBook.addOrder(order);

        if (trade != null) {
        	 tradeService.addTrade(trade);

            System.out.println(
                "TRADE EXECUTED -> "
                + trade.getSymbol()
                + " Price: "
                + trade.getPrice()
                + " Quantity: "
                + trade.getQuantity()
            );
        }

        return savedOrder;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getOrdersBySymbol(String symbol) {
        return orderRepository.findBySymbol(symbol);
    }
    public List<Order> getOrdersSorted(String field) {

        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }
    public Page<Order> getOrdersWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return orderRepository.findAll(pageable);
    }

    public Order updateOrder(Long id, Order orderDetails) {

        Order order = orderRepository.findById(id).orElse(null);

        if (order != null) {
            order.setSymbol(orderDetails.getSymbol());
            order.setSide(orderDetails.getSide());
            order.setPrice(orderDetails.getPrice());
            order.setQuantity(orderDetails.getQuantity());

            return orderRepository.save(order);
        }

        return null;
    }

    public void deleteOrder(Long id) {
    	 logger.info("Deleting order with id: " + id);
        orderRepository.deleteById(id);
    }
public OrderBook getOrderBook() {
    return orderBook;
}
}
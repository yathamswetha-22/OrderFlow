package com.orderflow.model;

import java.util.PriorityQueue;
import com.orderflow.entity.Order;

public class OrderBookResponse {

    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBookResponse(
            PriorityQueue<Order> buyOrders,
            PriorityQueue<Order> sellOrders) {

        this.buyOrders = buyOrders;
        this.sellOrders = sellOrders;
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(PriorityQueue<Order> buyOrders) {
        this.buyOrders = buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(PriorityQueue<Order> sellOrders) {
        this.sellOrders = sellOrders;
    }
}
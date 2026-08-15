package com.orderflow.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.orderflow.entity.Order;

public class OrderServiceTest {

    @Test
    void testOrderCreation() {

        Order order = new Order();

        order.setSymbol("TCS");
        order.setSide("BUY");
        order.setPrice(3500.0);
        order.setQuantity(10);

        assertEquals("TCS", order.getSymbol());
        assertEquals("BUY", order.getSide());
        assertEquals(3500.0, order.getPrice());
        assertEquals(10, order.getQuantity());
    }
}
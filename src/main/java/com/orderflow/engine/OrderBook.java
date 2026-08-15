package com.orderflow.engine;

import org.springframework.stereotype.Component;
import java.util.PriorityQueue;
import java.util.Comparator;

import com.orderflow.entity.Order;
import com.orderflow.model.Trade;

@Component
public class OrderBook {
	
	private PriorityQueue<Order> buyOrders =
		    new PriorityQueue<>(
		        Comparator.comparing(Order::getPrice).reversed()
		                  .thenComparing(Order::getTimestamp)
		    );
	private PriorityQueue<Order> sellOrders =
		    new PriorityQueue<>(
		        Comparator.comparing(Order::getPrice)
		                  .thenComparing(Order::getTimestamp)
		    );

	public PriorityQueue<Order> getBuyOrders() {
		return buyOrders;
	}
	
    public PriorityQueue<Order> getSellOrders() {
    	 return sellOrders;
    	}
    
    public Trade addOrder(Order order) {

        if ("BUY".equalsIgnoreCase(order.getSide())) {

            for (Order sell : sellOrders) {

            	if (sell.getPrice() <= order.getPrice()) {

                    return new Trade(
                            order.getSymbol(),
                            order.getPrice(),
                            Math.min(order.getQuantity(),
                                     sell.getQuantity()));
                }
            }

            buyOrders.add(order);
        }

        else {

            for (Order buy : buyOrders) {

            	if (buy.getPrice() >= order.getPrice()) {

                    return new Trade(
                            order.getSymbol(),
                            order.getPrice(),
                            Math.min(order.getQuantity(),
                                     buy.getQuantity()));
                }
            }

            sellOrders.add(order);
        }

        return null;
    }
}
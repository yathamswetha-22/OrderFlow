package com.orderflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.orderflow.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findBySymbol(String symbol);

}
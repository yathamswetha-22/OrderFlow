    package com.orderflow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timestamp;

    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotBlank(message = "Side is required")
    private String side;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(Long id, Long timestamp, String symbol,
                 String side, Double price, Integer quantity) {
        this.id = id;
        this.timestamp = timestamp;
        this.symbol = symbol;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
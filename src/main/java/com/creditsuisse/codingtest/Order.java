package com.creditsuisse.codingtest;

public class Order {
    private final String userId;
    private final double orderQuantityInKg;
    private final double pricePerKg;
    private final OrderType orderType;

    public Order(String userId, double orderQuantityInKg, double pricePerKg, OrderType orderType) {
        this.userId = userId;
        this.orderQuantityInKg = orderQuantityInKg;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public double getOrderQuantityInKg() {
        return orderQuantityInKg;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}

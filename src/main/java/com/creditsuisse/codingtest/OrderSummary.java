package com.creditsuisse.codingtest;

public class OrderSummary {

    private final double orderQuantityInKg;
    private final double pricePerKg;
    private final OrderType orderType;

    public OrderSummary(double orderQuantityInKg, double pricePerKg, OrderType orderType) {
        this.orderQuantityInKg = orderQuantityInKg;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    // generated by IntelliJ
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderSummary that = (OrderSummary) o;

        if (Double.compare(that.orderQuantityInKg, orderQuantityInKg) != 0) return false;
        if (Double.compare(that.pricePerKg, pricePerKg) != 0) return false;
        return orderType == that.orderType;
    }

    // generated by IntelliJ
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(orderQuantityInKg);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pricePerKg);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        return result;
    }

    // generated by IntelliJ
    @Override
    public String toString() {
        return "OrderSummary{" +
                "orderQuantityInKg=" + orderQuantityInKg +
                ", pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                '}';
    }
}

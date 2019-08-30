package com.creditsuisse.codingtest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.creditsuisse.codingtest.OrderType.*;

public class LiveOrderBoard {

    private static final Comparator<OrderSummary> ORDER_SUMMARY_COMPARATOR = Comparator.comparingDouble(OrderSummary::getPricePerKg);
    private static final Comparator<OrderSummary> REVERSE_ORDER_SUMMARY_COMPARATOR = Comparator.comparingDouble(OrderSummary::getPricePerKg).reversed();

    private final Map<UUID, Order> orders = new HashMap<>();

    public UUID addOrder(Order order) {
        assert order != null;
        UUID uuid = UUID.randomUUID();
        orders.put(uuid, order);
        return uuid;
    }

    public Order removeOrder(UUID orderId) {
        return orders.remove(orderId);
    }

    public List<OrderSummary> getOrderSummary() {

        List<OrderSummary> buySummary = orderSummaryFor(BUY, REVERSE_ORDER_SUMMARY_COMPARATOR);

        List<OrderSummary> sellSummary = orderSummaryFor(SELL, ORDER_SUMMARY_COMPARATOR);

        return Stream.concat(buySummary.stream(), sellSummary.stream()).collect(Collectors.toList());
    }

    private List<OrderSummary> orderSummaryFor(OrderType orderType, Comparator<OrderSummary> comparator) {
        Map<Double, List<Order>> ordersGroupedByPrice = orders.values().stream()
                .filter(order -> order.getOrderType() == orderType)
                .collect(Collectors.groupingBy(Order::getPricePerKg));
        return ordersGroupedByPrice.entrySet().stream()
                .map(LiveOrderBoard::summaryItemForPrice)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private static OrderSummary summaryItemForPrice(Map.Entry<Double, List<Order>> entry) {
        Double quantitySum = entry.getValue().stream().collect(Collectors.summingDouble(Order::getOrderQuantityInKg));
        return new OrderSummary(quantitySum, entry.getKey(), entry.getValue().get(0).getOrderType());
    }
}

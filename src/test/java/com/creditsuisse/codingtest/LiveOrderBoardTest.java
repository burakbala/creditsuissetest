package com.creditsuisse.codingtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class LiveOrderBoardTest {

    @Test
    public void addShouldAddANewOrder() {
        Order order = new Order("user1", 1, 2, OrderType.BUY);
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();
        liveOrderBoard.addOrder(order);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(new OrderSummary(1, 2, OrderType.BUY));

        assertEquals(expected, actual);
    }

    @Test
    public void addShouldCreateSeparateOrdersWhenCalledWithSameOrderTwice() {
        Order order = new Order("user1", 1, 2, OrderType.BUY);
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        UUID order1Id = liveOrderBoard.addOrder(order);
        UUID order2Id = liveOrderBoard.addOrder(order);

        assertNotEquals(order1Id, order2Id);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(2, 2, OrderType.BUY)
        );

        assertEquals(expected, actual);
    }

    @Test
    public void removeShouldRemoveExistingOrder() {
        Order order = new Order("user1", 1, 2, OrderType.BUY);
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();
        UUID orderId = liveOrderBoard.addOrder(order);
        liveOrderBoard.removeOrder(orderId);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotThrowExceptionWhenANonExistingOrderIsRemoved() {

        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        liveOrderBoard.removeOrder(UUID.randomUUID());

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    public void getOrderSummaryShouldReturnOrdersGroupedByOrderType() {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        Order buyOrder = new Order("user1", 1, 2, OrderType.BUY);
        Order sellOrder = new Order("user1", 3, 4, OrderType.SELL);

        liveOrderBoard.addOrder(buyOrder);
        liveOrderBoard.addOrder(sellOrder);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(1, 2, OrderType.BUY),
                new OrderSummary(3, 4, OrderType.SELL)
        );

        assertEquals(expected, actual);
    }

    @Test
    public void getOrderSummaryShouldReturnBuyOrdersOrdersDescendingByPrice() {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        Order buyOrder1 = new Order("user1", 1, 2, OrderType.BUY);
        Order buyOrder2 = new Order("user1", 3, 4, OrderType.BUY);

        liveOrderBoard.addOrder(buyOrder1);
        liveOrderBoard.addOrder(buyOrder2);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(3, 4, OrderType.BUY),
                new OrderSummary(1, 2, OrderType.BUY)
        );

        assertEquals(expected, actual);
    }

    @Test
    public void getOrderSummaryShouldReturnSellOrdersOrdersAscendingByPrice() {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        Order buyOrder1 = new Order("user1", 3, 4, OrderType.SELL);
        Order buyOrder2 = new Order("user1", 1, 2, OrderType.SELL);


        liveOrderBoard.addOrder(buyOrder1);
        liveOrderBoard.addOrder(buyOrder2);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(1, 2, OrderType.SELL),
                new OrderSummary(3, 4, OrderType.SELL)

        );

        assertEquals(expected, actual);
    }

    @Test
    public void getOrderSummaryShouldGroupBuyOrdersByPrice() {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        Order buyOrder1 = new Order("user1", 7, 8, OrderType.BUY);
        Order buyOrder2 = new Order("user1", 1, 2, OrderType.BUY);
        Order buyOrder3 = new Order("user1", 2, 2, OrderType.BUY);


        liveOrderBoard.addOrder(buyOrder1);
        liveOrderBoard.addOrder(buyOrder2);
        liveOrderBoard.addOrder(buyOrder3);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(7, 8, OrderType.BUY),
                new OrderSummary(3, 2, OrderType.BUY)
        );

        assertEquals(expected, actual);
    }

    @Test
    public void getOrderSummaryShouldGroupSellOrdersByPrice() {
        LiveOrderBoard liveOrderBoard = new LiveOrderBoard();

        Order buyOrder1 = new Order("user1", 7, 8, OrderType.SELL);
        Order buyOrder2 = new Order("user1", 1, 2, OrderType.SELL);
        Order buyOrder3 = new Order("user1", 2, 2, OrderType.SELL);


        liveOrderBoard.addOrder(buyOrder1);
        liveOrderBoard.addOrder(buyOrder2);
        liveOrderBoard.addOrder(buyOrder3);

        List<OrderSummary> actual = liveOrderBoard.getOrderSummary();

        List<OrderSummary> expected = Arrays.asList(
                new OrderSummary(3, 2, OrderType.SELL),
                new OrderSummary(7, 8, OrderType.SELL)
        );

        assertEquals(expected, actual);
    }
}
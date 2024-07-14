package com.example.axoncqrseventsourcingdemo.querymodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderConfirmedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderCreatedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderShippedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.queries.FindAllOrderedProductsQuery;
import com.example.axoncqrseventsourcingdemo.coreapi.queries.Order;
import com.example.axoncqrseventsourcingdemo.coreapi.queries.OrderStatus;

@Service
public class OrdersEventHandler {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();//= new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {
        orders.put(event.getOrderId(), new Order(event.getOrderId(), event.getProduct());
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        orders.get(event.getOrderId()).setOrderStatus(OrderStatus.CONFIRMED);
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        orders.get(event.getOrderId()).setOrderStatus(OrderStatus.SHIPPED);
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return List.copyOf(orders.values());
    }
}

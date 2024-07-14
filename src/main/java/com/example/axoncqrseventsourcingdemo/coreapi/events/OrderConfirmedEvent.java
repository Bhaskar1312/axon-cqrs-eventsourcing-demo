package com.example.axoncqrseventsourcingdemo.coreapi.events;

public class OrderConfirmedEvent {
    private final String orderId;

    public OrderConfirmedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderConfirmedEvent{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}

package com.example.axoncqrseventsourcingdemo.coreapi.events;

import java.util.Objects;

public class OrderShippedEvent {
    private final String orderId;

    public OrderShippedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderShippedEvent that = (OrderShippedEvent) o;

        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return orderId != null ? orderId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderShippedEvent{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}

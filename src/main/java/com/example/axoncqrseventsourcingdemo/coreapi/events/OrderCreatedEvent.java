package com.example.axoncqrseventsourcingdemo.coreapi.events;

import java.util.Objects;

public class OrderCreatedEvent {
    private final String orderId;
    private final String product;

    public OrderCreatedEvent(String orderId, String product) {
        this.orderId = orderId;
        this.product = product;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderCreatedEvent that = (OrderCreatedEvent) o;

        if (!Objects.equals(orderId, that.orderId)) {
            return false;
        }
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderCreatedEvent{" +
            "orderId='" + orderId + '\'' +
            ", product='" + product + '\'' +
            '}';
    }
}

package com.example.axoncqrseventsourcingdemo.coreapi.commands;

import java.util.Objects;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand {

    // The TargetAggregateIdentifier annotation tells Axon that the annotated field is an id of a given aggregate to which the command should be targeted.
    @TargetAggregateIdentifier
    private final String orderId;
    private final String product;

    public CreateOrderCommand(String orderId, String product) {
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

        CreateOrderCommand that = (CreateOrderCommand) o;

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
        return "CreateOrderCommand{" +
            "orderId='" + orderId + '\'' +
            ", product='" + product + '\'' +
            '}';
    }
}

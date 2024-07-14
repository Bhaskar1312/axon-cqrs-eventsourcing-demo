package com.example.axoncqrseventsourcingdemo.coreapi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    public ShipOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "ShipOrderCommand{" +
            "orderId='" + orderId + '\'' +
            '}';
    }
}
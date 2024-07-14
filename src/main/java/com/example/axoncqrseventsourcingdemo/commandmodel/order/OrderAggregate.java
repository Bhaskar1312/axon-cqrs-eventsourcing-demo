package com.example.axoncqrseventsourcingdemo.commandmodel.order;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.axoncqrseventsourcingdemo.commandmodel.exceptions.UnConfirmedOrderException;
import com.example.axoncqrseventsourcingdemo.coreapi.commands.ConfirmOrderCommand;
import com.example.axoncqrseventsourcingdemo.coreapi.commands.CreateOrderCommand;
import com.example.axoncqrseventsourcingdemo.coreapi.commands.ShipOrderCommand;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderConfirmedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderCreatedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderShippedEvent;

// The Aggregate annotation is an Axon Spring specific annotation marking this class as an aggregate. It will
// notify the framework that the required CQRS and Event Sourcing specific building blocks need to be instantiated
// for this OrderAggregate.
@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getProduct()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        if(orderConfirmed) {
            return;
        }
        apply(new OrderConfirmedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if(!orderConfirmed) {
            throw new UnConfirmedOrderException("Cannot ship an order which has not been confirmed yet.");
        }
        apply(new OrderShippedEvent(orderId));
    }

    protected OrderAggregate() {
    }

}

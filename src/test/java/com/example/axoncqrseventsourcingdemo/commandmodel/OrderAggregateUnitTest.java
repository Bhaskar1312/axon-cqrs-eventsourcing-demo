package com.example.axoncqrseventsourcingdemo.commandmodel;


import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.axoncqrseventsourcingdemo.commandmodel.exceptions.UnConfirmedOrderException;
import com.example.axoncqrseventsourcingdemo.commandmodel.order.OrderAggregate;
import com.example.axoncqrseventsourcingdemo.coreapi.commands.CreateOrderCommand;
import com.example.axoncqrseventsourcingdemo.coreapi.commands.ShipOrderCommand;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderConfirmedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderCreatedEvent;
import com.example.axoncqrseventsourcingdemo.coreapi.events.OrderShippedEvent;

class OrderAggregateUnitTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    String orderId = UUID.randomUUID().toString();
    String productId = "Deluxe Chair";


    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    void giveNoPriorActivity_whenCreateOrderCommand_thenShouldPublishOrderCreatedEvent() {
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId, productId))
                .expectEvents(new OrderCreatedEvent(orderId, productId));
    }

    @Test
    void givenOrderCreatedEvent_whenShipOrderCommand_thenShouldThrowUnconfirmedOrderException() {
        fixture.given(new OrderCreatedEvent(orderId, productId))
            .when(new ShipOrderCommand(orderId))
            .expectException(UnConfirmedOrderException.class);
    }

    @Test
    void givenOrderCreatedEventAndOrderConfirmedEvent_whenShipOrderCommand_thenShouldPublishOrderShippedEvent() {
        fixture.given(new OrderCreatedEvent(orderId, productId), new OrderConfirmedEvent(orderId))
            .when(new ShipOrderCommand(orderId))
            .expectEvents(new OrderShippedEvent(orderId));
    }
}

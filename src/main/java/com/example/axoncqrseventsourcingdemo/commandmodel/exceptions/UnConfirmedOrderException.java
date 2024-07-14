package com.example.axoncqrseventsourcingdemo.commandmodel.exceptions;

public class UnConfirmedOrderException extends RuntimeException {
    public UnConfirmedOrderException(String message) {
        super(message);
    }
}

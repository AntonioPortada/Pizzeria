package com.antonio.Pizzeria.service.exception;

public class EmailApiException extends RuntimeException {

    public EmailApiException() {
        super("Error sending email...");
    }
}

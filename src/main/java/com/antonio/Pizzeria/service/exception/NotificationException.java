package com.antonio.Pizzeria.service.exception;

public class NotificationException extends RuntimeException {

    public NotificationException() {
        super("Error sending notification...");
    }
}

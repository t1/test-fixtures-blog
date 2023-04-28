package com.example.fixturesdoku.order;

import jakarta.ws.rs.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(int orderId) {
        super("can't find order with id " + orderId);
    }
}

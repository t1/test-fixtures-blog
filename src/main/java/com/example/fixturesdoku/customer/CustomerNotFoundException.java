package com.example.fixturesdoku.customer;

import jakarta.ws.rs.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(int customerId) {
        super("can't find customer with id " + customerId);
    }
}

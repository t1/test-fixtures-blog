package com.example.fixturesdoku.product;

import jakarta.ws.rs.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(int productId) {
        super("can't find product with id " + productId);
    }
}

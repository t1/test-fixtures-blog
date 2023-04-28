package com.example.fixturesdoku.order;

import com.example.fixturesdoku.customer.Customer;
import com.example.fixturesdoku.product.Product;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Singleton
@Startup
@Slf4j
public class DbInit {
    @PersistenceContext
    EntityManager db;

    @PostConstruct
    void init() {
        var existing = db.createQuery("select o from Order o", Order.class)
            .getResultList();
        log.debug("found {} orders", existing.size());
        if (existing.size() > 0) return;
        log.debug("initialize database");

        var customer = Customer.builder()
            .name("Jane Doe")
            .discount(5)
            .build();
        db.persist(customer);

        var product0 = Product.builder()
            .name("Chair")
            .price(101)
            .build();
        db.persist(product0);

        var orderItem0 = OrderLine.builder()
            .amount(3)
            .product(product0)
            .build();
        db.persist(orderItem0);

        var product1 = Product.builder()
            .name("Table")
            .price(1001)
            .build();
        db.persist(product1);

        var orderItem1 = OrderLine.builder()
            .amount(2)
            .product(product1)
            .build();
        db.persist(orderItem1);

        var order = Order.builder()
            .date(LocalDate.parse("2023-03-09"))
            .customer(customer)
            .line(orderItem0)
            .line(orderItem1)
            .build();
        db.persist(order);
    }
}

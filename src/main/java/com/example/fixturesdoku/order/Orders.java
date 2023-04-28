package com.example.fixturesdoku.order;

import com.example.fixturesdoku.customer.Customers;
import com.example.fixturesdoku.product.Products;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.NonNull;
import org.eclipse.microprofile.graphql.Query;

import java.time.LocalDate;
import java.util.List;

@Path("/orders")
@GraphQLApi
@Dependent
public class Orders {
    @PersistenceContext
    EntityManager orders;

    @Inject Products products;
    @Inject Customers customers;

    @GET
    @Query public @NonNull List<Order> orders() {
        return orders.createQuery("select o from Order o", Order.class)
            .getResultList();
    }

    @GET @Path("/{id}")
    @Query public @NonNull Order order(@PathParam("id") int id) {
        var order = orders.find(Order.class, id);
        if (order == null) throw new OrderNotFoundException(id);
        return order;
    }

    @GET @Path("/{id}/sum")
    @Query public @NonNull int orderSum(@PathParam("id") int id) {
        return order(id).getSum();
    }

    @POST
    @Transactional
    @Mutation public @NonNull Order order(OrderInput input) {
        var customer = customers.customer(input.customerId);
        var order = Order.builder()
            .customer(customer)
            .date(LocalDate.now())
            .lines((input.lines == null) ? List.of() : input.lines.stream().map(this::map).toList())
            .build();
        orders.persist(order);
        return order;
    }

    private OrderLine map(OrderLineInput input) {
        var product = products.product(input.productId);
        var line = OrderLine.builder()
            .amount(input.amount)
            .product(product)
            .build();
        orders.persist(line);
        return line;
    }

    @DELETE @Path("/{id}")
    @Transactional
    @Mutation public @NonNull Order deleteOrder(@PathParam("id") int id) {
        var order = order(id);
        orders.remove(order);
        return order;
    }
}

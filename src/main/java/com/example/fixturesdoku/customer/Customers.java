package com.example.fixturesdoku.customer;

import jakarta.enterprise.context.Dependent;
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

import java.util.List;

@Path("/customers")
@GraphQLApi
@Dependent
public class Customers {
    @PersistenceContext EntityManager customers;

    @GET
    @Query public List<Customer> customers() {
        return customers.createQuery("select c from Customer c", Customer.class)
            .getResultList();
    }

    @GET @Path("/{id}")
    @Query public @NonNull Customer customer(@PathParam("id") int id) {
        var customer = customers.find(Customer.class, id);
        if (customer == null) throw new CustomerNotFoundException(id);
        return customer;
    }

    @POST
    @Transactional
    @Mutation public @NonNull Customer customer(Customer customer) {
        if (customer.id == null) {
            customers.persist(customer);
        } else {
            customer = customers.merge(customer);
        }
        return customer;
    }

    @DELETE @Path("/{id}")
    @Transactional
    @Mutation public @NonNull Customer deleteCustomer(@PathParam("id") int id) {
        var customer = customer(id);
        customers.remove(customer);
        return customer;
    }
}

package com.example.fixturesdoku.product;

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

@Path("/products")
@GraphQLApi
@Dependent
public class Products {
    @PersistenceContext
    EntityManager products;

    @GET
    @Query public @NonNull List<Product> products() {
        return products.createQuery("select p from Product p", Product.class)
            .getResultList();
    }

    @GET @Path("/{id}")
    @Query public @NonNull Product product(@PathParam("id") int id) {
        var product = products.find(Product.class, id);
        if (product == null) throw new ProductNotFoundException(id);
        return product;
    }

    @POST
    @Transactional
    @Mutation public @NonNull Product product(Product product) {
        if (product.id == null) {
            products.persist(product);
        } else {
            product = products.merge(product);
        }
        return product;
    }

    @DELETE @Path("/{id}")
    @Transactional
    @Mutation public @NonNull Product deleteProduct(@PathParam("id") int id) {
        var product = product(id);
        products.remove(product);
        return product;
    }
}

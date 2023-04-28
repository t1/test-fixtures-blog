package test;

import com.example.fixturesdoku.customer.Customer;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import static test.TestData.*;
import static test.TestData.api;

public class CustomerFixture implements Fixture<Customer> {
    static CustomerFixture someCustomer() {
        return new CustomerFixture();
    }

    @Path("/customers")
    public interface CustomerApi {
        @POST
        Customer create(Customer customer);
    }

    private final CustomerApi customers = api(CustomerApi.class);

    private int discount = someInt();

    public CustomerFixture withDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public Customer setup() {
        var customer = new Customer();
        customer.setName("Customer-Name-" + someInt());
        customer.setDiscount(discount);
        return customers.create(customer);
    }
}

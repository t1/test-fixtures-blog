package test;

import com.example.fixturesdoku.customer.Customer;
import com.example.fixturesdoku.order.Order;
import com.example.fixturesdoku.order.Order.OrderBuilder;
import com.example.fixturesdoku.order.OrderLine;
import com.example.fixturesdoku.product.Product;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import static test.TestData.*;

public class OrderFixture implements Fixture<Order> {
    static OrderFixture someOrder() {
        return new OrderFixture();
    }

    @Path("/orders")
    public interface OrderApi {
        @POST
        Order place(Order order);
    }

    private final OrderApi orders = api(OrderApi.class);

    private final OrderBuilder builder = Order.builder()
        .date(someLocalDate());

    public OrderFixture fromCustomer(Customer customer) {
        this.builder.customerId(customer.getId());
        return this;
    }

    public OrderFixture with(int amount, Product product) {
        this.builder.line(OrderLine.builder()
            .id(someInt())
            .amount(amount)
            .productId(product.getId())
            .build());
        return this;
    }

    @Override
    public Order setup() {
        return orders.place(builder.build());
    }
}

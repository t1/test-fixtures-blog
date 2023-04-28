package test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static test.CustomerFixture.someCustomer;
import static test.Fixture.given;
import static test.OrderFixture.someOrder;
import static test.ProductFixture.someProduct;
import static test.TestData.api;

class OrderSystemTest {
    @Path("/orders")
    public interface OrderApi {
        @GET @Path("/{id}/sum")
        int orderSum(@PathParam("id") int id);
    }

    OrderApi orders = api(OrderApi.class);


    @Test
    void shouldSumPriceWithDiscount() {
        var customer = given(someCustomer().withDiscount(5));
        var productAt10 = given(someProduct().withPrice(10));
        var productAt15 = given(someProduct().withPrice(15));
        var order = given(someOrder().fromCustomer(customer)
            .with(7, productAt10)
            .with(12, productAt15));

        var sum = orders.orderSum(order.getId());

        then(sum).isEqualTo(237);
    }
}

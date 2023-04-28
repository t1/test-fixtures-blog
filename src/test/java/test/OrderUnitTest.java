package test;

import com.example.fixturesdoku.customer.Customer;
import com.example.fixturesdoku.order.Order;
import com.example.fixturesdoku.order.OrderLine;
import com.example.fixturesdoku.product.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.BDDAssertions.then;

class OrderUnitTest {
    @Test
    void shouldSumPriceWithDiscount() {
        var order = Order.builder()
            .id(1)
            .date(LocalDate.ofEpochDay(2))
            .customer(Customer.builder()
                .id(3)
                .name("Customer-Name-4")
                .discount(5)
                .build())
            .line(OrderLine.builder()
                .id(6)
                .amount(7)
                .product(Product.builder()
                    .id(8)
                    .name("Product-Name-9")
                    .price(10)
                    .build())
                .build())
            .line(OrderLine.builder()
                .id(11)
                .amount(12)
                .product(Product.builder()
                    .id(13)
                    .name("Product-Name-14")
                    .price(15)
                    .build())
                .build())
            .build();

        var sum = order.getSum();

        then(sum).isEqualTo(237);
    }
}

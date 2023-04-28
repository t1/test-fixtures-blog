package test;

import com.example.fixturesdoku.product.Product;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import static com.example.fixturesdoku.product.Product.ProductBuilder;
import static test.TestData.api;
import static test.TestData.someInt;

public class ProductFixture implements Fixture<Product> {
    public static ProductFixture someProduct() {
        return new ProductFixture();
    }

    @Path("/products")
    public interface ProductApi {
        @POST
        Product create(Product product);
    }

    private final ProductApi products = api(ProductApi.class);

    private final ProductBuilder builder = Product.builder()
        .name("Product-Name-" + someInt())
        .price(someInt());

    public ProductFixture withPrice(int price) {
        this.builder.price(price);
        return this;
    }

    @Override
    public Product setup() {
        return products.create(builder.build());
    }
}

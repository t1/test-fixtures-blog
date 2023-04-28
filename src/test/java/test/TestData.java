package test;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;
import java.time.LocalDate;

public class TestData {
    private static int nextInt;

    static int someInt() {
        return nextInt++;
    }

    static LocalDate someLocalDate() {
        return LocalDate.ofEpochDay(someInt());
    }

    static <T> T api(Class<T> api) {
        return RestClientBuilder.newBuilder()
            .baseUri(URI.create("http://localhost:8080/rest"))
            .build(api);
    }
}

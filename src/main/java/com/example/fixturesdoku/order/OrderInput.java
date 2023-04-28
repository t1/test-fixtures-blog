package com.example.fixturesdoku.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.With;
import org.eclipse.microprofile.graphql.Input;

import java.util.List;

@Input("OrderInput")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor @With
public class OrderInput {
    int customerId;
    @Singular List<OrderLineInput> lines;
}

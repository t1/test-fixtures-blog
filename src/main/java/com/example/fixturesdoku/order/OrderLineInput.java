package com.example.fixturesdoku.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import org.eclipse.microprofile.graphql.Input;

@Input("OrderLineInput")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor @With
public class OrderLineInput {
    int amount;
    int productId;
}

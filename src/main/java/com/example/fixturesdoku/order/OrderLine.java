package com.example.fixturesdoku.order;

import com.example.fixturesdoku.product.Product;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor @With
public class OrderLine {
    @Id @GeneratedValue(strategy = SEQUENCE)
    Integer id;

    Integer amount;

    @ManyToOne
    Product product;

    Integer productId;

    @JsonbTransient
    public int getSum() {
        return amount * product.getPrice();
    }
}

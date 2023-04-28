package com.example.fixturesdoku.order;

import com.example.fixturesdoku.customer.Customer;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity @Table(name = "OrderTable")
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = SEQUENCE)
    Integer id;

    LocalDate date;

    @ManyToOne
    Customer customer;

    Integer customerId;

    @OneToMany(fetch = EAGER) @Singular
    List<OrderLine> lines;

    @JsonbTransient
    public int getSum() {
        return getLines().stream().mapToInt(OrderLine::getSum).sum()
               * (100 - customer.getDiscount()) / 100;
    }
}

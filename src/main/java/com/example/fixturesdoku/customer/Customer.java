package com.example.fixturesdoku.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor @With
public class Customer {
    @Id @GeneratedValue(strategy = SEQUENCE)
    Integer id;

    String name;

    int discount;
}

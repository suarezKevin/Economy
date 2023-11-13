package com.suarez.economy.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TypeOfAmortization {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "type_amortization_id")
    private UUID id;

    @Column(length = 50, nullable = false)
    private String name;

    public TypeOfAmortization setName(String name) {
        this.name = name;
        return this;
    }

}

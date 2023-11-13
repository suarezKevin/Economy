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
public class IndirectCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "indirect_charges_id")
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;

}

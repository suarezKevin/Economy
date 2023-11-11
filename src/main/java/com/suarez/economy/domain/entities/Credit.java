package com.suarez.economy.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "credit_id")
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "minimum_amount",nullable = false)
    private Double minimumamount;

    @Column(name = "maximum_amount",nullable = false)
    private Double maximumamount;

    @Column(name = "interest_rate",nullable = false)
    private Double interestrate;

    @Column(name = "created_at",nullable = false)
    private Date createdat;

    @ManyToOne(fetch = FetchType.LAZY)
    private Institution institution;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "credit")
    private List<Amortization> amortizations = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "credit")
    private List<IndirectCharges> indirectCharges = new ArrayList<>();

}

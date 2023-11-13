package com.suarez.economy.domain.entities;

import com.suarez.economy.util.enums.TypeOfAmortization;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Amortization {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private TypeOfAmortization typeOfAmortization;

    @ManyToOne(fetch = FetchType.LAZY)
    private Credit credit;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}

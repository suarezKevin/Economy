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

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "role_id")
    private UUID id;

    @Column(length = 50, nullable = false)
    private String name;

}

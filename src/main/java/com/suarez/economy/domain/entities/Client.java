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

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String identification;
    private String fullname;
    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String address;
    private String password;
    private Boolean status;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "client")
    private List<Amortization> amortizations = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.status = true;
    }

}

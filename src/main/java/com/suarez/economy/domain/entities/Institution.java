package com.suarez.economy.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "institution_id")
    private UUID id;

    @Column(length = 50, nullable = false)
    private String name;

    private String logo;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "institution")
    private Set<User> users = new HashSet<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "institution")
    private List<Credit> credits = new ArrayList<>();

    public void addUser(@NotNull User user){
        users.add(user);
    }
}

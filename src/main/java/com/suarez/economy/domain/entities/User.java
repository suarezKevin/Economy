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

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(length = 10, nullable = false ,unique = true)
    private String identification;

    @Column(length = 50, nullable = false)
    private String fullname;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false ,unique = true)
    private String telephone;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "user_id")
            ,inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToOne(fetch =  FetchType.LAZY)
    private Institution institution;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Amortization> amortizations = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.status = true;
    }

}

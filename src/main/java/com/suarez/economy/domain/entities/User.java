package com.suarez.economy.domain.entities;

import com.suarez.economy.util.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Column(name = "user_id")
    private UUID id;

    @Column(length = 10, nullable = false ,unique = true)
    private String identification;

    @Column(length = 50, nullable = false)
    private String fullname;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false ,unique = true)
    private String telephone;

    @Column(length = 100, nullable = false ,unique = true)
    private String address;

    @Column(length = 100, nullable = false ,unique = true)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 13)
    private Role role;

    @ManyToOne(fetch =  FetchType.LAZY)
    private Institution institution;

    @PrePersist
    public void generateStatus(){
        status = true;
    }

}

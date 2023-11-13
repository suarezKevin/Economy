package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r JOIN r.users u WHERE u.id = :userId")
    Collection<? extends GrantedAuthority> findRolesById(@Param("userId") UUID userId);

}

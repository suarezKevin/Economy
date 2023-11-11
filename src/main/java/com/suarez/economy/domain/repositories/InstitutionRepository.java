package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstitutionRepository extends JpaRepository<Institution, UUID> {

    boolean existsByName(String name);

}

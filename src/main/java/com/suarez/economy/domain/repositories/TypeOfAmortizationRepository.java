package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.TypeOfAmortization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TypeOfAmortizationRepository extends JpaRepository<TypeOfAmortization, UUID> {
}

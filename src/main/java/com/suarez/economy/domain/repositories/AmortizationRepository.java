package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.Amortization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmortizationRepository extends JpaRepository<Amortization, UUID> {
}

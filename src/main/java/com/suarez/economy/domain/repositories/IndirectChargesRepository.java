package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.IndirectCharges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndirectChargesRepository extends JpaRepository<IndirectCharges, UUID> {
}

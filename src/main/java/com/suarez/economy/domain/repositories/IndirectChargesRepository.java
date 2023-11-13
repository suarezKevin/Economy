package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.IndirectCharges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IndirectChargesRepository extends JpaRepository<IndirectCharges, UUID> {

    List<IndirectCharges> findAllByCredit_Id(UUID idCredit);

}

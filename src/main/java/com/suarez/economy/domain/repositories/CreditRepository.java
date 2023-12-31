package com.suarez.economy.domain.repositories;

import com.suarez.economy.domain.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

    List<Credit> findAllByInstitution_Id(UUID idInstitution);

}

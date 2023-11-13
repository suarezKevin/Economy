package com.suarez.economy.service.abstract_services;

import com.suarez.economy.api.models.requests.CreditRequest;
import com.suarez.economy.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ICreditService {

    ResponseEntity<CustomAPIResponse<?>> save(CreditRequest request, UUID institutionid);

    ResponseEntity<CustomAPIResponse<?>> getAll(UUID institutionid);

    ResponseEntity<CustomAPIResponse<?>> getAllCreditAndChargeByInstitutionId(UUID institutionid);

    ResponseEntity<CustomAPIResponse<?>> update(UUID id, CreditRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByID(UUID id);

    ResponseEntity<CustomAPIResponse<?>> delete(UUID id);

}

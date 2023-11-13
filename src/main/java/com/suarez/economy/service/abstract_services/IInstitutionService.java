package com.suarez.economy.service.abstract_services;

import com.suarez.economy.api.models.requests.InstitutionRequest;
import com.suarez.economy.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IInstitutionService {

    ResponseEntity<CustomAPIResponse<?>> save(InstitutionRequest request);

    ResponseEntity<CustomAPIResponse<?>> getAll();

    ResponseEntity<CustomAPIResponse<?>> update(UUID id, InstitutionRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByID(UUID id);

}

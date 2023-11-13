package com.suarez.economy.service.abstract_services;

import com.suarez.economy.api.models.requests.IndirectChargesRequest;
import com.suarez.economy.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IIndirectChargesService {

    ResponseEntity<CustomAPIResponse<?>> save(UUID creditid, IndirectChargesRequest request);

    ResponseEntity<CustomAPIResponse<?>> getAll(UUID creditid);

    ResponseEntity<CustomAPIResponse<?>> update(UUID chargesid, IndirectChargesRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByID(UUID chargesid);

    ResponseEntity<CustomAPIResponse<?>> delete(UUID chargesid);

}

package com.suarez.economy.service.abstract_services;

import com.suarez.economy.api.models.requests.UserRequest;
import com.suarez.economy.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface IUserService {

    ResponseEntity<CustomAPIResponse<?>> register(UserRequest request);

    ResponseEntity<CustomAPIResponse<?>> update(UUID id, UserRequest request);

    ResponseEntity<CustomAPIResponse<?>> save(UserRequest request, UUID companyId);

}

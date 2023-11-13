package com.suarez.economy.service.abstract_services;

import com.suarez.economy.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface ITypeOfAmortizationService {

    ResponseEntity<CustomAPIResponse<?>> getAll();

}

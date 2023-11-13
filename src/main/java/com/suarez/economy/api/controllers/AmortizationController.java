package com.suarez.economy.api.controllers;

import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.service.abstract_services.ITypeOfAmortizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/type-amortization")
public class AmortizationController {

    private final ITypeOfAmortizationService amortizationService;

    public AmortizationController(ITypeOfAmortizationService amortizationService) {
        this.amortizationService = amortizationService;
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getAll(){
        return amortizationService.getAll();
    }

}

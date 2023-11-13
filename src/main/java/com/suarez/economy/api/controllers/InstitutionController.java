package com.suarez.economy.api.controllers;

import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.service.abstract_services.IInstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "protected/institution")
public class InstitutionController {

    private final IInstitutionService institutionService;

    public InstitutionController(IInstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CustomAPIResponse<?>> getAll(){
        return institutionService.getAll();
    }

}

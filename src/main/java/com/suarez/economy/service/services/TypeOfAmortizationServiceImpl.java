package com.suarez.economy.service.services;

import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.entities.TypeOfAmortization;
import com.suarez.economy.domain.repositories.TypeOfAmortizationRepository;
import com.suarez.economy.service.abstract_services.ITypeOfAmortizationService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TypeOfAmortizationServiceImpl implements ITypeOfAmortizationService {

    private final TypeOfAmortizationRepository amortizationRepository;

    private final CustomResponseBuilder responseBuilder;

    public TypeOfAmortizationServiceImpl(TypeOfAmortizationRepository amortizationRepository, CustomResponseBuilder responseBuilder) {
        this.amortizationRepository = amortizationRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll() {
        List<TypeOfAmortization> amortizationList = amortizationRepository.findAll();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Tipos de Amortización.", amortizationList);
    }
}

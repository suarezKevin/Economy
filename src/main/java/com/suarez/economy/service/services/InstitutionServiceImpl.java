package com.suarez.economy.service.services;

import com.suarez.economy.api.models.requests.InstitutionRequest;
import com.suarez.economy.api.models.responses.InstitutionResponse;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.entities.Credit;
import com.suarez.economy.domain.entities.Institution;
import com.suarez.economy.domain.repositories.InstitutionRepository;
import com.suarez.economy.service.abstract_services.IInstitutionService;
import com.suarez.economy.util.mappers.InstitutionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class InstitutionServiceImpl implements IInstitutionService {

    private final InstitutionRepository institutionRepository;

    private final CustomResponseBuilder responseBuilder;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository, CustomResponseBuilder responseBuilder) {
        this.institutionRepository = institutionRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(InstitutionRequest request) {
        Institution institution = InstitutionMapper.INSTANCE.institutionFromInstitutionRequest(request);
        InstitutionResponse institutionResponse = InstitutionMapper.INSTANCE.institutionResponseFromInstitution(institutionRepository.save(institution));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Institución creada con exito!", institutionResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll() {
        List<Institution> institutionList = institutionRepository.findAll();
        List<InstitutionResponse> institutionResponseList = institutionList.stream().map(InstitutionMapper.INSTANCE::institutionResponseFromInstitution).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Instituciones", institutionResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(UUID id, InstitutionRequest request) {
        Institution institutionToEdit = institutionRepository.findById(id).orElseThrow(()-> new RuntimeException("Institución no encontrada!"));
        institutionToEdit.setName(request.getName());
        institutionToEdit.setLogo(request.getLogo());
        InstitutionResponse institutionResponse = InstitutionMapper.INSTANCE.institutionResponseFromInstitution(institutionRepository.save(institutionToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Institución actualizado exitosamente!", institutionResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByID(UUID id) {
        Institution institution = institutionRepository.findById(id).orElseThrow(()-> new RuntimeException("Institución no encontrada!"));
        InstitutionResponse institutionResponse = InstitutionMapper.INSTANCE.institutionResponseFromInstitution(institution);
        return responseBuilder.buildResponse(HttpStatus.OK, "Institución encontrado exitosamente!", institutionResponse);
    }
}

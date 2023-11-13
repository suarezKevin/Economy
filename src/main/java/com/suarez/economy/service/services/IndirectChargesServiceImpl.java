package com.suarez.economy.service.services;


import com.suarez.economy.api.models.requests.IndirectChargesRequest;
import com.suarez.economy.api.models.responses.IndirectChargesResponse;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.entities.Credit;
import com.suarez.economy.domain.entities.IndirectCharges;
import com.suarez.economy.domain.repositories.CreditRepository;
import com.suarez.economy.domain.repositories.IndirectChargesRepository;
import com.suarez.economy.service.abstract_services.IIndirectChargesService;
import com.suarez.economy.util.mappers.IChargesMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class IndirectChargesServiceImpl implements IIndirectChargesService {

    private final CreditRepository creditRepository;

    private final IndirectChargesRepository chargesRepository;

    private final CustomResponseBuilder responseBuilder;

    public IndirectChargesServiceImpl(CreditRepository creditRepository, IndirectChargesRepository chargesRepository, CustomResponseBuilder responseBuilder) {
        this.creditRepository = creditRepository;
        this.chargesRepository = chargesRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(UUID creditid, IndirectChargesRequest request) {
        Credit credit = creditRepository.findById(creditid).orElseThrow(()-> new RuntimeException("Crédito no encontrado!"));
        IndirectCharges charges = IChargesMapper.INSTANCE.indirectChargesFromIndirectChargesRequest(request);
        charges.setCredit(credit);
        IndirectChargesResponse chargesResponse = IChargesMapper.INSTANCE.indirectChargesResponseFromIndirectCharges(chargesRepository.save(charges));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Cargo creado exitosamente",chargesResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll(UUID creditid) {
        List<IndirectCharges> charges = chargesRepository.findAllByCredit_Id(creditid);
        List<IndirectChargesResponse> chargesResponseList = charges.stream().map(IChargesMapper.INSTANCE::indirectChargesResponseFromIndirectCharges).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Cargos", chargesResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(UUID chargesid, IndirectChargesRequest request) {
        IndirectCharges chargesToEdit = chargesRepository.findById(chargesid).orElseThrow(()-> new RuntimeException("Cargo no encontrado"));
        chargesToEdit.setName(request.getName());
        chargesToEdit.setDescription(request.getDescription());
        chargesToEdit.setAmount(request.getAmount());
        IndirectChargesResponse chargesResponse = IChargesMapper.INSTANCE.indirectChargesResponseFromIndirectCharges(chargesRepository.save(chargesToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Cargo actualizado exitosamente!", chargesResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByID(UUID chargesid) {
        IndirectCharges charges = chargesRepository.findById(chargesid).orElseThrow(()-> new RuntimeException("Cargo no encontrado"));
        IndirectChargesResponse chargesResponse = IChargesMapper.INSTANCE.indirectChargesResponseFromIndirectCharges(charges);
        return responseBuilder.buildResponse(HttpStatus.OK, "Cargo encontrado exitosamente", chargesResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(UUID chargesid) {
        IndirectCharges charges = chargesRepository.findById(chargesid).orElseThrow(()-> new RuntimeException("Cargo no encontrado"));
        chargesRepository.delete(charges);
        return responseBuilder.buildResponse(HttpStatus.OK, "Cargo eliminado exitosamente!");
    }
}

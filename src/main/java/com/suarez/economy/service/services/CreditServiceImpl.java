package com.suarez.economy.service.services;

import com.suarez.economy.api.models.requests.CreditRequest;
import com.suarez.economy.api.models.responses.CreditResponse;
import com.suarez.economy.api.models.responses.CreditResponseDTO;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.entities.Credit;
import com.suarez.economy.domain.entities.IndirectCharges;
import com.suarez.economy.domain.entities.Institution;
import com.suarez.economy.domain.repositories.CreditRepository;
import com.suarez.economy.domain.repositories.IndirectChargesRepository;
import com.suarez.economy.domain.repositories.InstitutionRepository;
import com.suarez.economy.service.abstract_services.ICreditService;
import com.suarez.economy.util.mappers.CreditMapper;
import com.suarez.economy.util.mappers.IChargesMapper;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CreditServiceImpl implements ICreditService {

    private final CreditRepository creditRepository;

    private final IndirectChargesRepository chargesRepository;

    private final InstitutionRepository institutionRepository;

    private final CustomResponseBuilder responseBuilder;

    public CreditServiceImpl(CreditRepository creditRepository, IndirectChargesRepository chargesRepository, InstitutionRepository institutionRepository, CustomResponseBuilder responseBuilder) {
        this.creditRepository = creditRepository;
        this.chargesRepository = chargesRepository;
        this.institutionRepository = institutionRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(CreditRequest request, UUID institutionid) {
        Institution institution = institutionRepository.findById(institutionid).orElseThrow(()-> new RuntimeException("Institutción no encontrada!"));
        Credit credit = CreditMapper.INSTANCE.creditFromCreditRequest(request);
        credit.setInstitution(institution);
        CreditResponse creditResponse = CreditMapper.INSTANCE.creditResponseFromCredit(creditRepository.save(credit));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Crédito creado exitosamente!", creditResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll(UUID institutionid) {
        List<Credit> creditList = creditRepository.findAllByInstitution_Id(institutionid);
        List<CreditResponse> creditResponseList = creditList.stream().map(CreditMapper.INSTANCE::creditResponseFromCredit).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Créditos", creditResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAllCreditAndChargeByInstitutionId(UUID institutionid) {
        List<Credit> creditList = creditRepository.findAllByInstitution_Id(institutionid);
        List<CreditResponseDTO> creditResponseList = creditList.stream().map(credit -> {
            List<IndirectCharges> chargesList = chargesRepository.findAllByCredit_Id(credit.getId());
            CreditResponseDTO creditResponseDTO = CreditMapper.INSTANCE.creditResponseDTOFromCredit(credit);
            for (IndirectCharges indirectCharge : chargesList) {
                creditResponseDTO.addIndirectChargesResponse(IChargesMapper.INSTANCE.indirectChargesResponseFromIndirectCharges(indirectCharge));
            }
            return creditResponseDTO;
        }).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Créditos con sus Cargos.", creditResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(UUID idcredit, CreditRequest request) {
        Credit creditToEdit = creditRepository.findById(idcredit).orElseThrow(()-> new RuntimeException("Crédito no encontrado!"));
        creditToEdit.setName(request.getName());
        creditToEdit.setMinimumamount(request.getMinimumamount());
        creditToEdit.setMaximumamount(request.getMaximumamount());
        creditToEdit.setInterestrate(request.getInterestrate());
        CreditResponse creditResponse = CreditMapper.INSTANCE.creditResponseFromCredit(creditRepository.save(creditToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Crédito actualizado exitosamente!", creditResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByID(UUID idcredit) {
        Credit credit = creditRepository.findById(idcredit).orElseThrow(()-> new RuntimeException("Crédito no encontrado!"));
        CreditResponse creditResponse = CreditMapper.INSTANCE.creditResponseFromCredit(credit);
        //creditResponse.setInstitution(credit.getInstitution().getId());
        return responseBuilder.buildResponse(HttpStatus.OK, "Crédito encontrado exitosamente!", creditResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(UUID idcredit) {
        Credit credit = creditRepository.findById(idcredit).orElseThrow(()-> new RuntimeException("Crédito no encontrado!"));
        creditRepository.delete(credit);
        return responseBuilder.buildResponse(HttpStatus.OK, "Crédito eliminado exitosamente!");
    }
}

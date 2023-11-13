package com.suarez.economy.util.mappers;

import com.suarez.economy.api.models.requests.CreditRequest;
import com.suarez.economy.api.models.responses.CreditResponse;
import com.suarez.economy.domain.entities.Credit;
import com.suarez.economy.domain.entities.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "amortizations", ignore = true)
    @Mapping(target = "indirectCharges", ignore = true)
    Credit creditFromCreditRequest(CreditRequest request);

    @Mapping(target = "id", source = "credit.id")
    @Mapping(target = "institution", source = "credit.institution.id")
    CreditResponse creditResponseFromCredit(Credit credit);

}

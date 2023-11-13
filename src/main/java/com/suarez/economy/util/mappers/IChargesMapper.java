package com.suarez.economy.util.mappers;

import com.suarez.economy.api.models.requests.IndirectChargesRequest;
import com.suarez.economy.api.models.responses.IndirectChargesResponse;
import com.suarez.economy.domain.entities.IndirectCharges;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IChargesMapper {

    IChargesMapper INSTANCE = Mappers.getMapper(IChargesMapper.class);

    @Mapping(target = "credit", ignore = true)
    IndirectCharges indirectChargesFromIndirectChargesRequest(IndirectChargesRequest request);

    IndirectChargesResponse indirectChargesResponseFromIndirectCharges(IndirectCharges charges);

}

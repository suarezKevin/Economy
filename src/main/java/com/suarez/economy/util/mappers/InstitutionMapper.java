package com.suarez.economy.util.mappers;

import com.suarez.economy.api.models.requests.InstitutionRequest;
import com.suarez.economy.api.models.responses.InstitutionResponse;
import com.suarez.economy.domain.entities.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

    InstitutionMapper INSTANCE = Mappers.getMapper(InstitutionMapper.class);

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "credits", ignore = true)
    Institution institutionFromInstitutionRequest(InstitutionRequest request);

    InstitutionResponse institutionResponseFromInstitution(Institution institution);

}

package com.suarez.economy.util.mappers;

import com.suarez.economy.api.models.requests.ClientRequest;
import com.suarez.economy.api.models.responses.ClientResponse;
import com.suarez.economy.domain.entities.Client;
import com.suarez.economy.security.model.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    UserPrincipal userPrincipalFromClient(Client client);
    ClientResponse clientResponseFromClient(Client client);

    Client clientFromClientRequest(ClientRequest request);

}

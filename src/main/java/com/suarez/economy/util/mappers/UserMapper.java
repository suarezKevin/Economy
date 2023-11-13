package com.suarez.economy.util.mappers;

import com.suarez.economy.api.models.requests.UserRequest;
import com.suarez.economy.api.models.responses.UserResponseDTO;
import com.suarez.economy.domain.entities.User;
import com.suarez.economy.util.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default List<SimpleGrantedAuthority> mapRolesToAuthorities(Role rol) {
        if (rol == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new SimpleGrantedAuthority(rol.name()));
    }

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "status", source = "user.status")
    @Mapping(target = "fullName", source = "user.fullname")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "identification", source = "user.identification")
    @Mapping(target = "telephone", source = "user.telephone")
    @Mapping(target = "roles", ignore = true)
    UserResponseDTO userResponseDTOFromUser(User user);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "institution", ignore = true)
    @Mapping(target = "fullname", source = "request.fullname")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "identification", source = "request.identification")
    @Mapping(target = "telephone", source = "request.telephone")
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", source = "request.password")
    @Mapping(target = "address", source = "request.address")
    User userFromUserRequest(UserRequest request);

}

package com.suarez.economy.api.models.responses;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;


public record UserResponse (
        UUID id,
        String identification,
        String fullname,
        String email,
        String telephone,
        Boolean status,
        Collection<?extends GrantedAuthority> authorities,
        InstitutionResponse institution
) {

}

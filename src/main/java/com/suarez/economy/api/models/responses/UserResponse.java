package com.suarez.economy.api.models.responses;

import com.suarez.economy.util.enums.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;


public record UserResponse (
        UUID id,
        String identification,
        String fullName,
        String email,
        String telephone,
        Boolean status,
        Collection<?extends GrantedAuthority> authorities,
        InstitutionResponse institution
) {

}

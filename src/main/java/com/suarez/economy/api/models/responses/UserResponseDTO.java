package com.suarez.economy.api.models.responses;

import com.suarez.economy.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UUID id;

    private String identification;

    private String fullName;

    private String email;

    private String telephone;

    private Boolean status;

    private Role role;

}

package com.suarez.economy.api.models.requests;

import com.suarez.economy.util.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "La identificación es obligatoria")
    @Size(max = 10, message = "El número de identificación no puede superar los 10 caracteres")
    private String identification;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 80, message = "El nombre completo no puede tener mas de 80 caracteres")
    private String fullName;

    @NotBlank(message = "El nombre de la institución es obligatorio")
    @Size(max = 80, message = "El nombre de la institución no puede tener mas de 80 caracteres")
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene el formato correcto")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telephone;

    @NotBlank(message = "El teléfono es obligatorio")
    private String password;

    private Boolean status;

    private String logo;

    @NotBlank(message = "El rol es obligatorio")
    private Role role;

}

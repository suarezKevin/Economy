package com.suarez.economy.api.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientRequest {

    @NotNull(message = "La identificación es obligatorio")
    private String identification;

    @NotNull(message = "El nombre completo es obligatorio")
    private String fullname;

    @NotNull(message = "El número de teléfono es obligatorio")
    private String telephone;

    @NotNull(message = "El correo electrónico es obligatorio")
    @Email(message = "Debe de tener un formato válido")
    private String email;

    @NotNull(message = "La dirección es obligatoria")
    private String address;

    @NotNull(message = "La contraseña es obligatoria")
    private String password;

    private Boolean status;


}

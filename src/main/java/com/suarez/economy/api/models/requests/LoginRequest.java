package com.suarez.economy.api.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

        @NotNull(message = "El correo es obligatorio")
        @Email(message = "Debe de tener un formato válido")
        private String email;
        @NotNull(message = "La contraseña es obligatoria")
        @Size( min = 8, message = "La contraseña debe tener minimo 8 caracteres")
        private String password;

}

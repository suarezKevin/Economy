package com.suarez.economy.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InstitutionRequest {

    @NotNull(message = "El nombre es obligatorio")
    private String name;

    private String logo;

}

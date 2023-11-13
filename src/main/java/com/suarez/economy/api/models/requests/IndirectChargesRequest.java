package com.suarez.economy.api.models.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndirectChargesRequest {

    @NotNull(message = "El nombre es obligatorio")
    private String name;

    private String description;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double amount;

}

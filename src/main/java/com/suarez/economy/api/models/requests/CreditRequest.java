package com.suarez.economy.api.models.requests;

import com.suarez.economy.domain.entities.Amortization;
import com.suarez.economy.domain.entities.IndirectCharges;
import com.suarez.economy.domain.entities.Institution;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditRequest {

    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El monto mínimo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double minimumamount;

    @NotNull(message = "El monto máximo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double maximumamount;

    @NotNull(message = "La tasa de interés es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double interestrate;

    private Institution institution;

}

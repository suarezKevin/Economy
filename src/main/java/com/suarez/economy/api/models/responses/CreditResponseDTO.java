package com.suarez.economy.api.models.responses;

import com.suarez.economy.domain.entities.IndirectCharges;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreditResponseDTO {

    private UUID id;

    private String name;

    private Double minimumamount;

    private Double maximumamount;

    private Double interestrate;

    private List<IndirectChargesResponse> indirectCharges = new ArrayList<>();

    public void addIndirectChargesResponse(IndirectChargesResponse chargesResponse){
        this.indirectCharges.add(chargesResponse);
    }

}

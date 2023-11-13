package com.suarez.economy.api.models.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class IndirectChargesResponse {

    private UUID id;

    private String name;

    private String description;

    private Double amount;

}

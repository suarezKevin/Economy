package com.suarez.economy.api.models.requests;

import lombok.Data;

@Data
public class AmortizationTableRequest {

    private double amount;

    private int term;

    private double interestrate;

}

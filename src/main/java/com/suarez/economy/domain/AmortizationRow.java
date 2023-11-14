package com.suarez.economy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AmortizationRow {

    private int numeroMes;
    private double cuota;
    private double chargesamount;
    private double interes;
    private double capital;
    private double saldo;

}

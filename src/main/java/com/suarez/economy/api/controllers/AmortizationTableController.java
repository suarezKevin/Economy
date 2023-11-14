package com.suarez.economy.api.controllers;


import com.suarez.economy.api.models.requests.AmortizationTableRequest;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.domain.AmortizationRow;
import jakarta.transaction.Transactional;
import org.apache.commons.math3.util.Precision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RestController
@RequestMapping(value = "public/generate-amortization")
public class AmortizationTableController {

    private final CustomResponseBuilder responseBuilder;

    public AmortizationTableController(CustomResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @GetMapping(value = "/french")
    public ResponseEntity<CustomAPIResponse<?>> generateFrenchAmortizationTable(@RequestBody final AmortizationTableRequest request){
        double interest = request.getInterestrate()/100;
        List<AmortizationRow> amortization = new ArrayList<>();
        double saldo = request.getAmount();
        double cuota = Precision.round(saldo * (interest / 12) / (1 - Math.pow(1 + interest / 12, -request.getTerm())),3);

        for (int i = 1; i <= request.getTerm(); i++) {
            double interes = Precision.round(saldo * interest / 12, 3);
            double capital = Precision.round(cuota - interes, 3);
            saldo -= capital;

            AmortizationRow row = new AmortizationRow(i, cuota, interes, capital, saldo);
            amortization.add(row);
        }
        return responseBuilder.buildResponse(HttpStatus.OK, "Tabla de Amortización Francesa", amortization);
    }

    @GetMapping(value = "/german")
    public ResponseEntity<CustomAPIResponse<?>> generateGermanAmortizationTable(@RequestBody final AmortizationTableRequest request){
        List<AmortizationRow> amortization = new ArrayList<>();
        double saldo = request.getAmount();
        double capital = Precision.round(saldo / request.getTerm(), 3);

        for (int i = 1; i <= request.getTerm(); i++) {
            double interes = Precision.round(saldo * (request.getInterestrate()/100) / 12, 3);
            double cuota = Precision.round(capital,3) + Precision.round(interes, 3);
            saldo -= capital;

            AmortizationRow row = new AmortizationRow(i, cuota, interes, capital, saldo);
            amortization.add(row);
        }
        return responseBuilder.buildResponse(HttpStatus.OK, "Tabla de Amortización Alemana", amortization);
    }

}

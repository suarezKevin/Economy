package com.suarez.economy.api.controllers;

import com.suarez.economy.api.models.requests.CreditRequest;
import com.suarez.economy.common.CustomAPIResponse;

import com.suarez.economy.service.abstract_services.ICreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(value = "protected/credit")
public class CreditController {

    private final ICreditService creditService;

    public CreditController(ICreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping(value = "/{institutionid}")
    public ResponseEntity<CustomAPIResponse<?>> save(@PathVariable final UUID institutionid, @RequestBody final CreditRequest request) {
        return creditService.save(request, institutionid);
    }

    @GetMapping(value = "/all/{institutionid}")
    public ResponseEntity<CustomAPIResponse<?>> getAll(@PathVariable final UUID institutionid){
        return creditService.getAll(institutionid);
    }

    @PutMapping(value = "/{idcredit}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final UUID idcredit, @RequestBody final CreditRequest request){
        return creditService.update(idcredit, request);
    }

    @GetMapping(value = "/one/{idcredit}")
    public ResponseEntity<CustomAPIResponse<?>> findByID(@PathVariable final UUID idcredit){
        return creditService.findByID(idcredit);
    }

    @DeleteMapping(value = "/{idcredit}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final UUID idcredit){
        return creditService.delete(idcredit);
    }

}

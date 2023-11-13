package com.suarez.economy.api.controllers;

import com.suarez.economy.api.models.requests.IndirectChargesRequest;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.service.abstract_services.IIndirectChargesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "protected/charges")
public class ChargesController {

    private final IIndirectChargesService chargesService;

    public ChargesController(IIndirectChargesService chargesService) {
        this.chargesService = chargesService;
    }

    @PostMapping(value = "/{idcredit}")
    public ResponseEntity<CustomAPIResponse<?>> save(@PathVariable final UUID idcredit, @RequestBody final IndirectChargesRequest request) {
        return chargesService.save(idcredit,request);
    }

    @GetMapping(value = "/all/{idcredit}")
    public ResponseEntity<CustomAPIResponse<?>> getAll(@PathVariable final UUID idcredit){
        return chargesService.getAll(idcredit);
    }

    @PutMapping(value = "/{idcharges}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final UUID idcharges, @RequestBody final IndirectChargesRequest request){
        return chargesService.update(idcharges, request);
    }

    @GetMapping(value = "/one/{idcharges}")
    public ResponseEntity<CustomAPIResponse<?>> findByID(@PathVariable final UUID idcharges){
        return chargesService.findByID(idcharges);
    }

    @DeleteMapping(value = "/{idcharges}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final UUID idcharges){
        return chargesService.delete(idcharges);
    }

}

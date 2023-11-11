package com.suarez.economy.api.controllers;

import com.suarez.economy.api.models.requests.UserRequest;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.service.abstract_services.IUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/protected/users")
@SecurityRequirement(name = "swagger")
public class UserController {

    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create/{institutionId}")
    public ResponseEntity<CustomAPIResponse<?>> create(@RequestBody UserRequest request, @PathVariable("institutionId")UUID institutionId){
        return userService.save(request, institutionId);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable("id") UUID id, @RequestBody UserRequest request){
        return userService.update(id,request);
    }

}

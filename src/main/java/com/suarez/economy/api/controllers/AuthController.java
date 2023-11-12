package com.suarez.economy.api.controllers;

import com.suarez.economy.api.models.requests.LoginRequest;
import com.suarez.economy.api.models.requests.UserRequest;
import com.suarez.economy.api.models.responses.JWTResponse;
import com.suarez.economy.common.CustomAPIResponse;
import com.suarez.economy.common.CustomResponseBuilder;
import com.suarez.economy.security.jwt.JWTProvider;
import com.suarez.economy.security.model.UserPrincipal;
import com.suarez.economy.service.abstract_services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "public/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    private final JWTProvider jwtProvider;

    private final CustomResponseBuilder responseBuilder;

    public AuthController(AuthenticationManager authenticationManager, IUserService userService, JWTProvider jwtProvider, CustomResponseBuilder responseBuilder) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping(value = "/logup")
    public ResponseEntity<CustomAPIResponse<?>> logUp(@RequestBody final UserRequest request){
        return userService.register(request);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CustomAPIResponse<?>> logIn(@RequestBody final LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario logeado exitosamente");
    }

}

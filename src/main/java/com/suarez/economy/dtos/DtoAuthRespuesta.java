package com.suarez.economy.dtos;

import com.suarez.economy.api.models.responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoAuthRespuesta {

    private String accessToken;
    private String tokenType = "Bearer ";
    private UserResponse user;

//    public DtoAuthRespuesta(String accessToken) {
//        this.accessToken = accessToken;
//    }

}

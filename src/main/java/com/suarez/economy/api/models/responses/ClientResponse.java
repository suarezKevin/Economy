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
public class ClientResponse {

    private UUID id;
    private String identification;
    private String fullname;
    private String telephone;
    private String email;
    private Boolean status;
    private String address;

}

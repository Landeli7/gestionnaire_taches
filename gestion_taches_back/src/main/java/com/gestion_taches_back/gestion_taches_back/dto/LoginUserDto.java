package com.gestion_taches_back.gestion_taches_back.dto;

import lombok.Data;

@Data
public class LoginUserDto {

    private Long id;
    private String email;
    private String pass;
}

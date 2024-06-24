package com.gestion_taches_back.gestion_taches_back.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String pass;
}

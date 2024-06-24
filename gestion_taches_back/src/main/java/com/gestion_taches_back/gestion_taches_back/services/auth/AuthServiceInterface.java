package com.gestion_taches_back.gestion_taches_back.services.auth;

import com.gestion_taches_back.gestion_taches_back.dto.UserDto;
import com.gestion_taches_back.gestion_taches_back.entity.User;

public interface AuthServiceInterface {

    Boolean doesUserExist(User userToVerify);
    UserDto createUser(User newUser);
    User loginUser(String email, String pass);
}

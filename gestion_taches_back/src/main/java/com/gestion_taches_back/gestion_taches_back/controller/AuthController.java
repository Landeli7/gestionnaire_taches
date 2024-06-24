package com.gestion_taches_back.gestion_taches_back.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gestion_taches_back.gestion_taches_back.dto.LoginUserDto;
import com.gestion_taches_back.gestion_taches_back.dto.UserDto;
import com.gestion_taches_back.gestion_taches_back.entity.User;
import com.gestion_taches_back.gestion_taches_back.services.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private HttpHeaders responseHeaders = new HttpHeaders();

    private String formatResp(String resp) {
        return "[\"" + resp + "\"]";
    }

    private void setHeadersContentType(MediaType mediaType) {
        responseHeaders.setContentType(mediaType);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody @Valid User user, BindingResult bindingResult) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        if (bindingResult.hasErrors()) {
            String errorMsg = "L'adresse email n'a pas été définie correctement.";
            // Validation errors
            if (user.getName().length() < 3 || user.getName().length() > 20) {
                errorMsg = "La longueur du champ 'Nom' doit être comprise entre 3 et 20 caractères.";
            } else if (user.getEmail().length() < 5 || user.getEmail().length() > 30) {
                errorMsg = "La longueur du champ 'Email' doit être comprise entre 5 et 30 caractères.";
            } else if (user.getPass().length() < 8 || user.getPass().length() > 50) {
                errorMsg = "La longueur du champ 'Mot de passe' doit être comprise entre 8 et 50 caractères.";
            }

            return new ResponseEntity<String>(formatResp(errorMsg), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        Boolean isNameOrEmailAlreadyTaken = authService.doesUserExist(user);
        if(isNameOrEmailAlreadyTaken) {
            return new ResponseEntity<String>(formatResp("Email ou nom déjà utilisé(s)."), responseHeaders, HttpStatus.BAD_REQUEST);
        }

        UserDto createdUserDto = authService.createUser(user);
        if (createdUserDto == null) {
            return new ResponseEntity<String>(formatResp("Erreur lors de la création du compte."), responseHeaders, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<String>(formatResp("Compte créé."), responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDto loginUserDto) {

        setHeadersContentType(MediaType.APPLICATION_JSON);

        User UserLoggedIn = authService.loginUser(loginUserDto.getEmail(), loginUserDto.getPass());
        if (UserLoggedIn == null) {
            return new ResponseEntity<String>(formatResp("Email ou mot de passe erroné(s)."), responseHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(formatResp(UserLoggedIn.getId().toString()), responseHeaders, HttpStatus.CREATED);
    }
}

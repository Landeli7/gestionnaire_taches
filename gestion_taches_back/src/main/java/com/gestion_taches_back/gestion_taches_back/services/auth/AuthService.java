package com.gestion_taches_back.gestion_taches_back.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestion_taches_back.gestion_taches_back.dto.UserDto;
import com.gestion_taches_back.gestion_taches_back.entity.User;
import com.gestion_taches_back.gestion_taches_back.repository.UserRepository;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Boolean doesUserExist(User userToVerify) {
        User user = userRepository.findByEmailOrName(userToVerify.getEmail(), userToVerify.getName());
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public UserDto createUser(User newUser) {
        User createdUser = userRepository.save(newUser);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    @Transactional
    public User loginUser(String email, String pass) {
        return userRepository.findByEmailAndPass(email, pass);
    }
}

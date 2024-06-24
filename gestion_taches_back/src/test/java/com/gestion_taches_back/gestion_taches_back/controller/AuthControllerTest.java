package com.gestion_taches_back.gestion_taches_back.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import com.gestion_taches_back.gestion_taches_back.controller.AuthController;
import com.gestion_taches_back.gestion_taches_back.dto.LoginUserDto;
import com.gestion_taches_back.gestion_taches_back.entity.User;

// @RunWith(SpringJUnit5ClassRunner.class)
@SpringBootTest
public class AuthControllerTest {

	// @Autowired
	// private AuthController authController;

	// private User testUser = new User();

	// public AuthControllerTest() {
	// 	testUser.setName("testName");
	// 	testUser.setEmail("test@email.com");
	// 	testUser.setPass("testPass");
	// }

	// // AUTH

	// @Test
	// public void authControllerLoads() throws Exception {
	// 	assertThat(this.authController).isNotNull();
	// }

	// @Test
	// void signup() throws Exception {
	// 	ResponseEntity<String> res = authController.signupUser(testUser, null);

	// 	assertThat(res.getBody()).isEqualTo("Compte créé.");
	// }

	// @Test
	// void login() throws Exception {
	// 	LoginUserDto testUserDto = new LoginUserDto();
	// 	testUserDto.setEmail(testUser.getEmail());
	// 	testUserDto.setPass(testUser.getPass());

	// 	ResponseEntity<String> res = authController.loginUser(testUserDto);

	// 	assertThat(res.getBody()).isNotEqualTo("Email ou mot de passe erroné(s).");
	// }
}

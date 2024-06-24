package com.gestion_taches_back.gestion_taches_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion_taches_back.gestion_taches_back.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailAndPass(final String email, final String pass);
    User findByEmailOrName(final String email, final String name);
}

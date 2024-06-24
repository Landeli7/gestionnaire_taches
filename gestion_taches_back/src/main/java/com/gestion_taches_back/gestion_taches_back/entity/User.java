package com.gestion_taches_back.gestion_taches_back.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

// import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", length=20)
    @Length(min = 3, max = 20)
    @NotNull
    private String name;

    @Column(name = "email", length=30)
    @Length(min = 5, max = 30)
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotNull
    private String email;
    
    @Column(name = "pass", length=50)
    @Length(min = 8, max = 50)
    @NotNull
    private String pass;

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    // private List<UserTask> userTask = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<UserTask> userTaskList;
}

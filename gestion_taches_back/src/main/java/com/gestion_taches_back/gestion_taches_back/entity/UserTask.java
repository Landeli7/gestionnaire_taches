package com.gestion_taches_back.gestion_taches_back.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "usertask")
public class UserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "name", length=20)
    @Length(min = 3, max = 20)
    @NotNull
    private String name;

    @Column(name = "desc", length=50)
    @Length(min = 5, max = 50)
    @NotNull
    private String desc;

    @Column(name = "completed")
    @NotNull
    private Boolean completed;

    @Column(name = "due_date")
    @NotNull
    private LocalDate dueDate;

    @Column(name = "priority")
    @NotNull
    private Integer priority;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(userId = "user.id")
    // private User user;
}

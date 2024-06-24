package com.gestion_taches_back.gestion_taches_back.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserTaskDto {

    private Long id;
    private Long userId;
    private String name;
    private String desc;
    private Boolean completed;
    private LocalDate dueDate;
    private Integer priority;
}

package com.gestion_taches_back.gestion_taches_back.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.EnableCommand;

@SpringBootApplication
@EnableCommand(taskShell.class)
public class taskShellApplication {
    public static void main(String[] args) {
        SpringApplication.run( taskShellApplication.class, args);
    }
}
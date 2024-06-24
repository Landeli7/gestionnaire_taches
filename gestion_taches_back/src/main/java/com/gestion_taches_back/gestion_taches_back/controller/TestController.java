package com.gestion_taches_back.gestion_taches_back.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("string")    
    public String getString() {
        return "Ceci est un test";
    }
}

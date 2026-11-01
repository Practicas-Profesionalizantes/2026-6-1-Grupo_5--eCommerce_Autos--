package com.back.carfind;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Prueba {

    @GetMapping("/")
    public String saludo() {
        return "¡Backend levantado con éxito!";
    }
}
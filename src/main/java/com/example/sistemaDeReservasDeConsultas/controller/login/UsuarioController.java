package com.example.sistemaDeReservasDeConsultas.controller.login;

import com.example.sistemaDeReservasDeConsultas.service.login.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping("/")
    public String home(){
        return "Olá!!";
    }

}

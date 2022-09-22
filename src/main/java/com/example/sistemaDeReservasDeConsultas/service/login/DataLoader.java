package com.example.sistemaDeReservasDeConsultas.service.login;

import com.example.sistemaDeReservasDeConsultas.enums.login.UsuarioRoles;
import com.example.sistemaDeReservasDeConsultas.model.login.Usuario;
import com.example.sistemaDeReservasDeConsultas.repository.login.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository repository;

    public DataLoader(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String passwordUser = encoder.encode("senhaUser");
        String passwordAdmin = encoder.encode("senhaAdmin");

        repository.save(new Usuario("user", passwordUser, UsuarioRoles.ROLE_USER));
        repository.save(new Usuario("admin", passwordAdmin, UsuarioRoles.ROLE_ADMIN));

    }
}

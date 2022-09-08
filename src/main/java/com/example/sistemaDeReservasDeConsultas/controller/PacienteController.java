package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.service.PacienteServiceImpl;
import com.example.sistemaDeReservasDeConsultas.entity.PacienteEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteServiceImpl service;

    public PacienteController(PacienteServiceImpl service) { this.service = service; }

    @PostMapping
    public PacienteEntity cadastrarPaciente(@RequestBody PacienteEntity paciente) {
        return service.add(paciente);
    }

    @GetMapping
    public List<PacienteEntity> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<PacienteEntity> buscaPacienteId(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/atualizar")
    public void alterarPaciente(@RequestBody PacienteEntity paciente) {
        service.update(paciente);
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) {
        service.remove(id);
    }
}

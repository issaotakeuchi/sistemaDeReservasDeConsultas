package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) { this.service = service; }

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) {
        return service.add(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscaPacienteId(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/atualizar")
    public void alterarPaciente(@RequestBody Paciente paciente) {
        service.update(paciente);
    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Long id) {
        service.remove(id);
    }
}

package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;

@Service
public class PacienteService {

    private final IPacienteRepository repository;

    public PacienteService(IPacienteRepository repository) { this.repository = repository; }

    public Paciente add(Paciente paciente) {
        if ( paciente != null) { return (Paciente) repository.save(paciente); }
        return new Paciente();
    }

    public List<Paciente> getAll() {
        return repository.findAll();
    }

    public Optional<Paciente> getById(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else { throw new IllegalStateException("Repositório nulo"); }
        
    }

    public void update(Paciente paciente) {
        if (paciente == null) { throw new IllegalStateException("Paciente nulo"); }
        Long id = paciente.getId();
        if (getById(id) == null) { throw new IllegalStateException("Paciente nulo"); }
        repository.save(paciente);
    }
    
}
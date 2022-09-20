package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;

@Service
public class PacienteService {

    private final IPacienteRepository repository;

    public PacienteService(IPacienteRepository repository) { this.repository = repository; }

    public Paciente add(Paciente paciente) {
        return repository.save(paciente);
    }

    public List<Paciente> getAll() {
        return repository.findAll();
    }

    public Paciente getById(Long id) {
        return repository.findById(id).get();
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Paciente update(Paciente paciente) {
        return repository.saveAndFlush(paciente);
    }
}
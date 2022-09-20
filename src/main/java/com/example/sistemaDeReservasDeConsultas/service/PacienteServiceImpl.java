package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;

@Service
public class PacienteServiceImpl implements IService<Paciente> {

    private final IPacienteRepository repository;

    public PacienteServiceImpl(IPacienteRepository repository) { this.repository = repository; }

    @Override
    public Paciente add(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public List<Paciente> getAll() {
        return repository.findAll();
    }

    @Override
    public Paciente getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return repository.saveAndFlush(paciente);
    }
}
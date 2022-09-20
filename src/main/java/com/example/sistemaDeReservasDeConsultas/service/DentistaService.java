package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaService {

    private final IDentistaRepository repository;

    public DentistaService(IDentistaRepository repository) { this.repository = repository; }

    public Dentista add(Dentista dentista) {
        return repository.save(dentista);
    }

    public List<Dentista> getAll() {
        return repository.findAll();
    }

    public Dentista getById(Long id) {
        return repository.findById(id).get();
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Dentista update(Dentista dentista) {
        return repository.saveAndFlush(dentista);
    }
}

package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaServiceImpl implements IService<Dentista> {

    private final IDentistaRepository repository;

    public DentistaServiceImpl(IDentistaRepository repository) { this.repository = repository; }

    @Override
    public Dentista add(Dentista dentista) {
        return repository.save(dentista);
    }

    @Override
    public List<Dentista> getAll() {
        return repository.findAll();
    }

    @Override
    public Dentista getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Dentista update(Dentista dentista) {
        return repository.saveAndFlush(dentista);
    }
}

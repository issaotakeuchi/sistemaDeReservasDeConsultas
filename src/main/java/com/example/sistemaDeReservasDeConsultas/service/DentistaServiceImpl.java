package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.entity.DentistaEntity;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaServiceImpl implements IDentistaService<DentistaEntity> {

    private final IDentistaRepository repository;

    public DentistaServiceImpl(IDentistaRepository repository) { this.repository = repository; }

    @Override
    public DentistaEntity add(DentistaEntity dentista) {
        if ( dentista != null) { return repository.save(dentista); }
        return new DentistaEntity();
    }

    @Override
    public List<DentistaEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DentistaEntity> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void remove(int id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else {
            throw new IllegalStateException("Repositório nulo"); }
    }

    @Override
    public void update(DentistaEntity dentista) {
        if (dentista == null) { throw new IllegalStateException("Dentista nulo"); }
        else if (dentista != null && repository.findById(dentista.getId()).isPresent()) {
            repository.saveAndFlush(dentista);
        }
    }
    
}

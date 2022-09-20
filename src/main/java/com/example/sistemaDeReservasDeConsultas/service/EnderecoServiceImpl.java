package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.repository.IEnderecoRepository;

@Service
public class EnderecoServiceImpl implements IService<Endereco> {

    private final IEnderecoRepository repository;

    public EnderecoServiceImpl(IEnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Endereco add(Endereco endereco) {
        return repository.save(endereco);
    }

    @Override
    public List<Endereco> getAll() {
        return repository.findAll();
    }

    @Override
    public Endereco getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Endereco update(Endereco endereco) {
        return repository.saveAndFlush(endereco);
    }
}

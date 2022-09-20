package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.repository.IEnderecoRepository;

@Service
public class EnderecoService {

    private final IEnderecoRepository repository;

    public EnderecoService(IEnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco add(Endereco endereco) {
        return repository.save(endereco);
    }


    public List<Endereco> getAll() {
        return repository.findAll();
    }

    public Endereco getById(Long id) {
        return repository.findById(id).get();
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Endereco update(Endereco endereco) {
        return repository.saveAndFlush(endereco);
    }
}

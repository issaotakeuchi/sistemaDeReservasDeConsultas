package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

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
        if (endereco != null) {
            return repository.save(endereco);
        }
        return new Endereco();
    }

    public List<Endereco> getAll() {
        return repository.findAll();
    }

    public Optional<Endereco> getById(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        if (getById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalStateException("Repositório nulo");
        }
    }

    public void update(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalStateException("Endereço nulo");
        } else if (endereco != null && repository.findById(endereco.getId()).isPresent()) {
            repository.save(endereco);
        }
    }

}

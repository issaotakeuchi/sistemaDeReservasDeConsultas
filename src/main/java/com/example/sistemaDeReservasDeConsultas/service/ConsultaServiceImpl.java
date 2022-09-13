package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.entity.ConsultaModel;
import com.example.sistemaDeReservasDeConsultas.repository.IConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IConsultaService<ConsultaModel> {
    private final IConsultaRepository repository;

    public ConsultaServiceImpl(IConsultaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ConsultaModel> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ConsultaModel> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ConsultaModel add(ConsultaModel consulta) {
        if (consulta != null) {
            return repository.save(consulta);
        }
        return new ConsultaModel();
    }

    @Override
    public void update(ConsultaModel consulta) {
        if (consulta == null) {
            throw new IllegalStateException("Consulta nulo");
        } else if (consulta != null && repository.findById(consulta.getId()).isPresent()) {
            repository.saveAndFlush(consulta);
        }
    }

    @Override
    public void remove(Long id) {
        if (getById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalStateException("Repositório nulo");
        }
    }
}



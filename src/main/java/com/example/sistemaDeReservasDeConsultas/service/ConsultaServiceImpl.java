package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.repository.IConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements IService<Consulta> {
    private final IConsultaRepository repository;

    public ConsultaServiceImpl(IConsultaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Consulta add(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public List<Consulta> getAll() {
        return repository.findAll();
    }

    @Override
    public Consulta getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Consulta update(Consulta consulta) {
        return repository.saveAndFlush(consulta);
    }
}



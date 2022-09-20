package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.repository.IConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    private final IConsultaRepository repository;

    public ConsultaService(IConsultaRepository repository) {
        this.repository = repository;
    }


    public Consulta add(Consulta consulta) {
        return repository.save(consulta);
    }


    public List<Consulta> getAll() {
        return repository.findAll();
    }


    public Consulta getById(Long id) {
        return repository.findById(id).get();
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Consulta update(Consulta consulta) {
        return repository.saveAndFlush(consulta);
    }
}



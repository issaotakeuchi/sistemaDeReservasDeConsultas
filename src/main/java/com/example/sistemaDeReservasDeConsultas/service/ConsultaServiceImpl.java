package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.repository.IConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl {
    private final IConsultaRepository repository;

    public ConsultaServiceImpl(IConsultaRepository repository) {
        this.repository = repository;
    }

    public Consulta add(Consulta consulta) {
        if (consulta != null) { return repository.save(consulta); }
        return new Consulta();
    }

    public List<Consulta> getAll() {
        return repository.findAll();
    }

    public Optional<Consulta> getById(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else {
            throw new IllegalStateException("Repositório nulo"); }
    }

    public void update(Consulta consulta) {
        if (consulta == null) { throw new IllegalStateException("Consulta nulo"); }
        else if (repository.findById(consulta.getId()).isPresent()) {
            repository./*saveAndFlush*/save(consulta);
        }
    }


}



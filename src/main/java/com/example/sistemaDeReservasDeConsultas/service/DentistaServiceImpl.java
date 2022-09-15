package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import org.springframework.stereotype.Service;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.repository.IDentistaRepository;

@Service
public class DentistaServiceImpl {

    private final IDentistaRepository repository;

    public DentistaServiceImpl(IDentistaRepository repository) { this.repository = repository; }

    public Dentista add(Dentista dentista) {
        if ( dentista != null) { return repository.save(dentista); }
        return new Dentista();
    }

    public List<Dentista> getAll() {
        return repository.findAll();
    }

    public Optional<Dentista> getById(Long id) {
        return repository.findById(id);
    }

    public void remove(Long id) {
        if (getById(id).isPresent()) { repository.deleteById(id); }
        else {
            throw new IllegalStateException("Repositório nulo"); }
    }

    public void update(Dentista dentista) {
        if (dentista == null) { throw new IllegalStateException("Dentista nulo"); }
        else if (dentista != null && repository.findById(dentista.getId()).isPresent()) {
            repository.save/*saveAndFlush*/(dentista);
        }
    }

    /*public Dentista addConsultaAoDentista(Consulta consulta){

    }*/
    
}

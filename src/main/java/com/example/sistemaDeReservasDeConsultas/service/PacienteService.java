package com.example.sistemaDeReservasDeConsultas.service;

import com.example.sistemaDeReservasDeConsultas.dao.IDao;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IDao<Paciente>{

    private final IPacienteRepository repo;

    public PacienteService(IPacienteRepository repo) {
        this.repo = repo;
    }

    public Paciente salvar(Paciente paciente) {
        if (paciente != null){
            return repo.save(paciente);
        }
        return new Paciente();
    }

    public List<Paciente> buscarTodos() {
        return repo.findAll();
    }

    public void alterar(Paciente paciente) {
        if (paciente != null && repo.findById(paciente.getId()).isPresent()){
            repo.saveAndFlush(paciente);
        }
        System.out.println("Paciente não encontrado");
    }

    public Optional<Paciente> buscarPorId(int id) {
        return repo.findById(id);
    }

    public void excluir(int id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }
        System.out.println("Paciente não encontrado");
    }
}

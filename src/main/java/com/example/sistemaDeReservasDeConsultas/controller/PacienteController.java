package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) throws SQLException{
        return service.salvar(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodosPacientes() throws SQLException{
        return service.buscarTodos();
    }

    @RequestMapping(value = "/buscaId")
    public Paciente buscaPacienteId(@RequestParam("id") int id) throws SQLException{
        return service.buscarPorId(id).isEmpty() ? new Paciente() : service.buscarPorId(id).get();
    }

    @PatchMapping
    public void alterarPaciente(@RequestBody Paciente paciente) throws SQLException{
        System.out.println();
        service.alterar(paciente);
    }

    @DeleteMapping
    public void excluirPaciente(@RequestParam("id") int id) throws SQLException{
        service.excluir(id);
    }
}

package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public Paciente cadastrarPaciente(@RequestBody Paciente paciente) {
        return service.salvar(paciente);
    }

    @GetMapping
    public List<Paciente> buscarTodosPacientes() {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> buscaPacienteId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PutMapping
    public void alterarPaciente(@RequestBody Paciente paciente) {
        System.out.println();
        service.alterar(paciente);
    }
//    @PutMapping
//    public ResponseEntity<Paciente> alterarPaciente(@RequestBody Paciente paciente) {
//        ResponseEntity re = null;
//        if(service.buscarPorId(paciente.getId()) == null){
//            re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return re;
//    }

    @DeleteMapping("/{id}")
    public void excluirPaciente(@PathVariable Integer id) {
        service.excluir(id);
    }
//    @DeleteMapping
//    public ResponseEntity<Paciente> excluir(@PathVariable Integer id) throws SQLException{
//        ResponseEntity re = null;
//        if (service.buscarPorId(id) == null){
//            re = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            re = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return re;
//    }
}

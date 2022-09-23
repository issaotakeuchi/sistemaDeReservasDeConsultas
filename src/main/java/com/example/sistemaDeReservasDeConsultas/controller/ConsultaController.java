package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.ResourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.add(consulta));
        } catch (Exception e) {
            throw new BadRequestException("Os dados da solicitação não correspondem aos necessários para o cadastro.");
        }
    }

    @RequestMapping
    public List<Consulta> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado a consulta buscada com id: " + id);
        }
    }

    @PutMapping("atualizar")
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody Consulta consulta) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.update(consulta));
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível atualizar os dados da consulta com os dados desta requisição");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            ResponseEntity.ok(service.getById(id));
            service.remove(id);
            return ResponseEntity.ok("Consulta excluída.");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível excluir a consulta de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

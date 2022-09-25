package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.ResourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.service.ConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;
    final static Logger log = LoggerFactory.getLogger(ConsultaController.class);

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Consulta> cadastrarConsulta(@RequestBody Consulta consulta) throws BadRequestException {
        try {
            log.info("Cadastrada nova consulta com sucesso.");
            return ResponseEntity.ok(service.add(consulta));
        } catch (Exception e) {
            log.info("Não foi possível cadastrar a consulta com base nas informações recebidas.");
            throw new BadRequestException("Os dados da solicitação não correspondem aos necessários para o cadastro.");
        }
    }

    @GetMapping
    public List<Consulta> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            service.getById(id);
            log.info("Encontrada a consulta solicitada de id: " + id);
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            log.info("Não foi encontrado a consulta solicitada de id: " + id);
            throw new ResourceNotFoundException("Não foi encontrado a consulta buscada com id: " + id);
        }
    }

    @PutMapping("atualizar")
    public ResponseEntity<Consulta> alterarConsulta(@RequestBody Consulta consulta) throws BadRequestException {
        try {
            service.getById(consulta.getId());
            service.update(consulta);
            log.info("A consulta de id: " + consulta.getId() + " foi atualizada com sucesso.");
            return ResponseEntity.ok(service.update(consulta));
        } catch (Exception e) {
            log.info("Não foi possível atualizar a consulta.");
            throw new BadRequestException("Não foi possível atualizar os dados da consulta com os dados desta requisição");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirConsulta(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            ResponseEntity.ok(service.getById(id));
            service.remove(id);
            log.info("A consulta de id: " + id + " foi localizada no banco de dados e removida com sucesso.");
            return ResponseEntity.ok("Consulta excluída.");
        } catch (Exception e) {
            log.info("Não foi encontrado a consulta de id " + id + " para efetuar a exclusão.");
            throw new ResourceNotFoundException("Não foi possível excluir a consulta de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

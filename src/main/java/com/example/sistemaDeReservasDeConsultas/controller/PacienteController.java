package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.ResourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")

public class PacienteController {

    private final PacienteService service;
    final static Logger log = LoggerFactory.getLogger(PacienteController.class);

    public PacienteController(PacienteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        try {
            log.info("Cadastrado com sucesso.");
            return ResponseEntity.ok(service.add(paciente));
        } catch (Exception e) {
            log.info("Dados para cadastro não correspondem ao necessário.");
            throw new BadRequestException("Os dados da solicitação não correspondem aos necessários para o cadastro.");
        }
    }

    @GetMapping
    public List<Paciente> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscaPacienteId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            service.getById(id);
            log.info("Encontrado o paciente de id: " + id);
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            log.info("Não foi encontrado o paciente de id: " + id);
            throw new ResourceNotFoundException("Não foi encontrado o paciente buscado com id: " + id);
        }
    }

    @PutMapping
    public ResponseEntity<Paciente> alterarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        try {
            service.getById(paciente.getId());
            service.update(paciente);
            log.info("Paciente de id: " + paciente.getId() + " foi atualizado com sucesso.");
            return ResponseEntity.ok(service.update(paciente));
        } catch (Exception e) {
            log.info("Não foi possível atualizar o paciente.");
            throw new BadRequestException("Não foi possível atualizar os dados do paciente com os dados desta requisição");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            service.remove(id);
            log.info("O paciente de id: " + id + " foi localizado no banco de dados e removido com sucesso.");
            return ResponseEntity.ok("Paciente excluído.");
        } catch (Exception e) {
            log.info("Não foi encontrado o paciente de id " + id + " para efetuar a exclusão.");
            throw new ResourceNotFoundException("Não foi possível excluir o paciente de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

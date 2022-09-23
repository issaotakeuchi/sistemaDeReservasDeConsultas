package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.ResourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.add(paciente));
        } catch (Exception e) {
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
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o paciente buscado com id: " + id);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Paciente> alterarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.update(paciente));
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível atualizar os dados do paciente com os dados desta requisição");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            ResponseEntity.ok(service.getById(id));
            service.remove(id);
            return ResponseEntity.ok("Paciente excluído.");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível excluir o paciente de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

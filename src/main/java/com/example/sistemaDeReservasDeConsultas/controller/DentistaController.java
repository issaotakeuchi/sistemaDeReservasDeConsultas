package com.example.sistemaDeReservasDeConsultas.controller;
import com.example.sistemaDeReservasDeConsultas.exceptions.BadRequestException;
import com.example.sistemaDeReservasDeConsultas.exceptions.ResourceNotFoundException;
import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.service.DentistaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private final DentistaService service;

    public DentistaController(DentistaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Dentista> cadastrarDentista(@RequestBody Dentista dentista) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.add(dentista));
        } catch (Exception e) {
            throw new BadRequestException("Os dados da solicitação não correspondem aos necessários para o cadastro.");
        }
    }

    @GetMapping
    public List<Dentista> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentista> buscaDentistaId(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi encontrado o dentista buscado com id: " + id);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Dentista> alterarDentista(@RequestBody Dentista dentista) throws BadRequestException {
        try {
            return ResponseEntity.ok(service.update(dentista));
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível atualizar os dados do dentista com os dados desta requisição");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirDentista(@PathVariable Long id) throws ResourceNotFoundException {
        try {
            service.remove(id);
            return ResponseEntity.ok("Dentista excluído.");
        } catch (Exception e) {
            throw new ResourceNotFoundException("Não foi possível excluir o dentista de id: " + id);
        }
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.service.ConsultaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public Consulta cadastrarConsulta(@RequestBody Consulta consulta){
        return service.add(consulta);
    }

    @RequestMapping
    public List<Consulta> buscarTodos() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Consulta> buscarConsultaId(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("atualizar")
    public void alterarConsulta(@RequestBody Consulta consulta) {
        service.update(consulta);
    }

    @DeleteMapping("/{id}")
    public void excluirConsulta(@PathVariable Long id) {
        service.remove(id);
    }

}

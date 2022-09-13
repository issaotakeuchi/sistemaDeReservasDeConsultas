package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.entity.ConsultaModel;
import com.example.sistemaDeReservasDeConsultas.service.ConsultaServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsultaController {
    private final ConsultaServiceImpl consultaService;

    public ConsultaController(ConsultaServiceImpl consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping("/consultas/adicionar")
    public ConsultaModel salvarConsulta(@RequestBody ConsultaModel consultaModel){
        return consultaService.add(consultaModel);
    }

    @RequestMapping(value = "/consultas", method = RequestMethod.GET, produces = "application/json")
    public List<ConsultaModel> buscarTodos() {
        return consultaService.getAll();
    }

    @GetMapping("/consultas/{id}")
    public Optional<ConsultaModel> buscarPorId(@PathVariable Long id) {
        return consultaService.getById(id);
    }

    @PutMapping("/consultas/atualizar")
    public void atualizarConsulta(@RequestBody ConsultaModel consulta) {
        consultaService.update(consulta);
    }

    @DeleteMapping("/consultas/delete/{id}")
    public void excluir(@PathVariable Long id) {
        consultaService.remove(id);
    }

}

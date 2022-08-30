package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/dentista")
public class DentistaController {
    @Autowired
    DentistaService dentistaService;

    @PostMapping
    public Dentista salvaDentista(@RequestBody Dentista dentista) throws SQLException{
        return dentistaService.salvar(dentista);
    }
    @GetMapping
    public List<Dentista> buscarTodos() throws SQLException{
        return dentistaService.buscarTodos();
    }
    @RequestMapping(value = "/buscaId")
    public Dentista buscarPorId(@RequestParam("id") int id) throws SQLException{
        return dentistaService.buscarPorId(id).isEmpty() ? new Dentista() : dentistaService.buscarPorId(id).get();
    }
    @PatchMapping
    public void alterar(@RequestBody Dentista dentista) throws SQLException {
        System.out.println();
        dentistaService.alterar(dentista);
    }
    @DeleteMapping
    public void excluir(@RequestParam("id") int id) throws SQLException {
        dentistaService.excluir(id);
    }
}

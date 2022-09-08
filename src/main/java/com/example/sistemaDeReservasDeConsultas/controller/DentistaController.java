package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.entity.DentistaEntity;
import com.example.sistemaDeReservasDeConsultas.service.DentistaServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DentistaController {

    private final DentistaServiceImpl dentistaService;

    public DentistaController(DentistaServiceImpl dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping("/dentista/adicionar")
    public DentistaEntity salvaDentista(@RequestBody DentistaEntity dentistaEntity){
        return dentistaService.add(dentistaEntity);
    }
    @RequestMapping(value = "/dentistas", method = RequestMethod.GET, produces = "application/json")
    public List<DentistaEntity> buscarTodos(){
        return dentistaService.getAll();
    }
    @GetMapping("/dentista/{id}")
    public Optional<DentistaEntity> buscarPorId(@PathVariable int id){
        return dentistaService.getById(id);
    }
    @PutMapping("/dentista/atualizar")
    public void atualizarDentista(@RequestBody DentistaEntity dentistaEntity){
        dentistaService.update(dentistaEntity);
    }
    @DeleteMapping("/dentista/delete/{id}")
    public void excluir(@PathVariable int id){
        dentistaService.remove(id);
    }
}
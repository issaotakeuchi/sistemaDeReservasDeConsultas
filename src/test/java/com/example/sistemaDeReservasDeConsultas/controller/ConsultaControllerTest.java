package com.example.sistemaDeReservasDeConsultas.controller;

import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ConsultaControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        Dentista dentista0 = new Dentista(1L,"issao","takeuchi","123");

        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentista0)));

        Paciente paciente0 = new Paciente(1L,"issao","takeuchi",null,"123",null);

        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente0)));

        Consulta consulta0 = new Consulta(1L,paciente0,dentista0,null);

        mockMvc.perform(MockMvcRequestBuilders.post("/consultas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(consulta0)));
    }

    @Test
    @DirtiesContext
    public void consultaTesteAdicionar() throws Exception {
        Dentista dentista = new Dentista(2L,"issao","takeuchi","123");

        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentista)));

        Paciente paciente = new Paciente(2L,"joao","silva",null,"123",null);

        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente)));

        Consulta consulta = new Consulta(2L,paciente,dentista,null);

        mockMvc.perform(MockMvcRequestBuilders.post("/consultas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(consulta)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void consultaTesteBuscarTodos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/consultas"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DirtiesContext
    public void consultaTesteBuscarPorID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/consultas/{id}",1))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DirtiesContext
    public void consultaTesteExcluir() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/consultas/{id}",1))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/consultas"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }

}
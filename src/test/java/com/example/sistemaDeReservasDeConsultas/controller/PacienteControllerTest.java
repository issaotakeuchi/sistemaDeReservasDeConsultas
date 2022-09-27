package com.example.sistemaDeReservasDeConsultas.controller;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PacienteControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        Paciente paciente1 = new Paciente(0L,"issao","takeuchi",null,"123",null);
        Paciente paciente2 = new Paciente(0L,"dani","fontoura",null,"123",null);
        Paciente paciente3 = new Paciente(0L,"carol","ms",null,"123",null);
        Paciente paciente4 = new Paciente(0L,"cris","fernandes",null,"123",null);
        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente1)));
        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente2)));
        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente3)));
        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente4)));
    }

    @Test
    @DirtiesContext
    public void pacienteTesteAdicionar() throws Exception {
        Paciente paciente0 = new Paciente(0L,"joao","silva",null,"123",null);

        mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente0)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void pacienteTesteBuscarTodos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DirtiesContext
    public void pacienteTesteBuscarPorID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",3))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.sobrenome").value("ms"));
    }

    @Test
    @DirtiesContext
    public void pacienteTesteExcluir() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",3))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/pacientes"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }
}
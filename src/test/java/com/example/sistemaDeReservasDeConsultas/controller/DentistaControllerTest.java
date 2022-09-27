package com.example.sistemaDeReservasDeConsultas.controller;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DentistaControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        Dentista dentista1 = new Dentista(0L,"issao","takeuchi","123");
        Dentista dentista2 = new Dentista(0L,"carol","ms","123");
        Dentista dentista3 = new Dentista(0L,"dani","fontoura","123");

        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentista1)));
        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentista2)));
        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dentista3)));
    }

    @Test
    @DirtiesContext
    public void dentistaTesteAdicionar() throws Exception {
        Dentista dentista0 = new Dentista(0L,"joao","silva","123");

        mockMvc.perform(MockMvcRequestBuilders.post("/dentistas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dentista0)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    public void dentistaTesteBuscarTodos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dentistas"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @DirtiesContext
    public void dentistaTesteBuscarPorID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dentistas/{id}",3))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.sobrenome").value("fontoura"));
    }

    @Test
    @DirtiesContext
    public void pacienteTesteExcluir() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/dentistas/{id}",3))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/dentistas"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().contentType("application/json"));
    }
}
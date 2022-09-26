package com.example.sistemaDeReservasDeConsultas;

import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

//@WebMvcTest(PacienteController.class)

class SistemaDeReservasDeConsultasApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PacienteService service;

	@JsonFormat(pattern = "dd/MM/yyyy")


	@Test
	public void cadastrarPaciente () throws Exception {

			String nome = "Daniel";
			String sobrenome = "Fontoura";
			Endereco endereco1 = new Endereco();
			String rg = "123456789-0";
			Date dataDeAlta = Date.valueOf("2022-11-11");

			Paciente paciente1 = new Paciente() {

				@Override
				public String getNome() {
					return nome;
				}

				@Override
				public String getSobrenome() {
					return sobrenome;
				}

				@Override
				public Endereco getEndereco(){
					return endereco1;
				}

				@Override
				public String getRg() {
					return rg;
				}

				@Override
				public Date getDataDeAlta(){
					return dataDeAlta;
				}
			};

			Gson gson = new Gson();
			String json = gson.toJson(paciente1);


			mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
							.contentType(APPLICATION_JSON)
							.content(json)
							.characterEncoding("utf-8"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andReturn();
	}

	@Test
	public void testBuscaPacientePeloId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",  "1")
				.accept(APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON));
		}

	@Test
	public void testListarConsultas() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/consultas")
				.accept(APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON));
	}




}

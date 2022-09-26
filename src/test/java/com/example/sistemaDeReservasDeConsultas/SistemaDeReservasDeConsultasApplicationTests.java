package com.example.sistemaDeReservasDeConsultas;

import com.example.sistemaDeReservasDeConsultas.controller.PacienteController;
import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import com.example.sistemaDeReservasDeConsultas.repository.IPacienteRepository;
import com.example.sistemaDeReservasDeConsultas.service.PacienteService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.Id;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.mockito.MockitoAnnotations.initMocks;
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
	private PacienteService pacienteService;

	@JsonFormat(pattern = "dd/MM/yyyy")

	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.openMocks(this);
		PacienteController controller = new PacienteController(pacienteService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}




	@Test
	public void cadastrarPaciente () throws Exception {

		Paciente paciente1 = new Paciente();
		paciente1.setId(1L);
		paciente1.setNome("Daniel");
		paciente1.setSobrenome("Fontoura");
		paciente1.setEndereco(null);
		paciente1.setRg("12345678-9");
		paciente1.setDataDeAlta(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(paciente1);

		Mockito.when(pacienteService.add(Mockito.any(Paciente.class))).thenReturn(paciente1);

		mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
					.content(requestJson)
					.contentType("application/json")
					.characterEncoding("utf-8"))
					.andDo(MockMvcResultHandlers.print())
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.nome").value("Daniel"))
					.andExpect(jsonPath("$.sobrenome").value("Fontoura"));
	}


	@Test
	public void testBuscaPacientePeloId() throws Exception{

		Paciente paciente2 = new Paciente();
		paciente2.setId(2L);
		paciente2.setNome("Dani");
		paciente2.setSobrenome("Sciacco");
		paciente2.setEndereco(null);
		paciente2.setRg("98765432-1");
		paciente2.setDataDeAlta(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson2 = ow.writeValueAsString(paciente2);

		Mockito.when(pacienteService.add(Mockito.any(Paciente.class))).thenReturn(paciente2);

		mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
				.content(requestJson2)
				.contentType("application/json")
				.characterEncoding("utf-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));

		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",  "2")
				.contentType("application/json")
				.characterEncoding("utf-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}

	@Test
	public void testListarConsultas() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/consultas")
				.contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"));
	}




}

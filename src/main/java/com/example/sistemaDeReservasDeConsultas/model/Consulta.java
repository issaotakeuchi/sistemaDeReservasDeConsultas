package com.example.sistemaDeReservasDeConsultas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "consultas")
public class Consulta {

    private Long id;
    private Paciente paciente;
    private Dentista dentista;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone =  "Brazil/East")
    private Date dataEHoraConsulta;
}

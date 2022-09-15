package com.example.sistemaDeReservasDeConsultas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "consultas")
public class Consulta {

    @Id
    private Long id;
    @Field(name = "paciente")
    private Paciente paciente;
    @Field(name = "dentista")
    private Dentista dentista;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone =  "Brazil/East")
    private Date dataEHoraConsulta;
}

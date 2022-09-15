package com.example.sistemaDeReservasDeConsultas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "Consulta")
@Document(collection = "consultas")
public class Consulta {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@ManyToOne
    //@JoinColumn(name = "paciente_id")
    private Paciente paciente;
    //@ManyToOne
    //@JoinColumn(name = "dentista_id")
    private Dentista dentista;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone =  "Brazil/East")
    private Date dataEHoraConsulta;
}

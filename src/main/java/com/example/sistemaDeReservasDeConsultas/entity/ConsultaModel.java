package com.example.sistemaDeReservasDeConsultas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "Consulta")
public class ConsultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;
    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private DentistaEntity dentista;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataEHoraConsulta;
}

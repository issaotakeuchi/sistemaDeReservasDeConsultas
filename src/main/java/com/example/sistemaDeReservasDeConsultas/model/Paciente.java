package com.example.sistemaDeReservasDeConsultas.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//@NoArgsConstructor
@Data
//@Entity @Table(name = "pacientes")
@Document(collection = "pacientes")
public class Paciente {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private String rg;
    private Date dataDeAlta;

    //@OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    //@JsonIgnore
    @Field(name = "consultas")
    private List<Consulta> consultas = new ArrayList<>();

    public void addConsultaAoPaciente(Consulta consulta){
        consultas.add(consulta);
    }
    
}

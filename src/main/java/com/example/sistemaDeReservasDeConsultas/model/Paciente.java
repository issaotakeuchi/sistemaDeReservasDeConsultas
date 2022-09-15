package com.example.sistemaDeReservasDeConsultas.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Data
@Document(collection = "pacientes")
public class Paciente {

    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    @Field(name = "endereco")
    private Endereco endereco;
    private String rg;
    private String dataDeAlta;
    private List<Consulta> consultas = new ArrayList<>();
    
}

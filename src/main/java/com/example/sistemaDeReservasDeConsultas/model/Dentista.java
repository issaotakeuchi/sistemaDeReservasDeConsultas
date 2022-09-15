package com.example.sistemaDeReservasDeConsultas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Document(collection = "dentistas")
public class Dentista {
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;
    private List<Consulta> consultas = new ArrayList<>();
}

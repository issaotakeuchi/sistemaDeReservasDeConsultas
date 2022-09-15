package com.example.sistemaDeReservasDeConsultas.model;

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

    private Long id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;
    @Field(name = "consultas")
    private List<Consulta> consultas = new ArrayList<>();

    public void addConsultaDentista(Consulta consulta){
        consultas.add(consulta);
    }
}

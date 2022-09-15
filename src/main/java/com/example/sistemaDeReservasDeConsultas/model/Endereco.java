package com.example.sistemaDeReservasDeConsultas.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "enderecos")
public class Endereco {

    @Id
    private Long id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String pais;

}
package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Endereco extends BaseEntity {

    private @Getter @Setter String rua;
    private @Getter @Setter String numero;
    private @Getter @Setter String bairro;
    private @Getter @Setter String cidade;
    private @Getter @Setter String uf;
    private @Getter @Setter String pais;

}
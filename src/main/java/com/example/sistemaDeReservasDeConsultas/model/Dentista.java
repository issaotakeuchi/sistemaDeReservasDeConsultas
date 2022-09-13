package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@ToString
public class Dentista extends Pessoa {
    private String matriculaCadastro;
}

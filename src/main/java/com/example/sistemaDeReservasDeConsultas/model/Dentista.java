package com.example.sistemaDeReservasDeConsultas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@ToString
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private @Getter @Setter Long id;
    private String nome;
    private String sobrenome;
    private String matriculaCadastro;
}

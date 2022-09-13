package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
public abstract class Pessoa extends BaseEntity {

    protected @Getter @Setter String nome;
    protected @Getter @Setter String sobrenome;
    
}

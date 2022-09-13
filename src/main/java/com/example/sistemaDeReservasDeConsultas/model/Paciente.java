package com.example.sistemaDeReservasDeConsultas.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private @Getter @Setter Long id;
    private @Getter @Setter String nome;
    private @Getter @Setter String sobrenome;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private @Getter @Setter Endereco endereco;
    private @Getter @Setter String rg;
    private @Getter @Setter Date dataDeAlta;

    public Paciente(String nome, String sobrenome, Endereco endereco, String rg, Date dataDeAlta) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.dataDeAlta = dataDeAlta;
    }

    
}

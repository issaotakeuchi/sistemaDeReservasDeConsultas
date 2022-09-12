package com.example.sistemaDeReservasDeConsultas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private @Getter @Setter Long id;
    private @Getter @Setter String rua;
    private @Getter @Setter String numero;
    private @Getter @Setter String bairro;
    private @Getter @Setter String cidade;
    private @Getter @Setter String uf;
    private @Getter @Setter String pais;

    public Endereco(String rua, String numero, String bairro, String cidade, String uf, String pais) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
    }

}
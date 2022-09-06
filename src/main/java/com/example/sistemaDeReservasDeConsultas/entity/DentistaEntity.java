package com.example.sistemaDeReservasDeConsultas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DentistaEntity extends BaseEntity {

    private String nome;
    private String sobrenome;
    private String matriculaCadastro;

    public DentistaEntity(Long id, String nome, String sobrenome, String matriculaCadastro) {
        super.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matriculaCadastro = matriculaCadastro;
    }

}
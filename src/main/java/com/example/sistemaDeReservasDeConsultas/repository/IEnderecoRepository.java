package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IEnderecoRepository extends /*JpaRepository*/MongoRepository<Endereco, Long> { }

package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends MongoRepository<Endereco, Long> { }

package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistaRepository extends MongoRepository<Dentista, Long> { }
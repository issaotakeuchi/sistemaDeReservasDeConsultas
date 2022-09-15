package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDentistaRepository extends /*JpaRepository*/MongoRepository<Dentista, Long> { }
package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPacienteRepository extends /*JpaRepository*/MongoRepository<Paciente, Long> { }

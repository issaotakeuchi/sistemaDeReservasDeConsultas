package com.example.sistemaDeReservasDeConsultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistaRepository extends JpaRepository<Dentista, Long> { }
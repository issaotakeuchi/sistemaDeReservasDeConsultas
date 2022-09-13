package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.entity.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaRepository extends JpaRepository<ConsultaModel, Long> {
}

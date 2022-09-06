package com.example.sistemaDeReservasDeConsultas.repository;

import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
}

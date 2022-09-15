package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends MongoRepository<Paciente, Long> {

}

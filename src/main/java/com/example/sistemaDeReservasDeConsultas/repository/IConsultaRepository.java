package com.example.sistemaDeReservasDeConsultas.repository;


import com.example.sistemaDeReservasDeConsultas.model.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultaRepository extends MongoRepository<Consulta, Long> {
}

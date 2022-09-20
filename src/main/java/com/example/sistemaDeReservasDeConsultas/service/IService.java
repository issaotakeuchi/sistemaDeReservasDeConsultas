package com.example.sistemaDeReservasDeConsultas.service;

import java.util.List;
import java.util.Optional;

public interface IService <T> {

    public List<T> getAll();
    public T getById(Long id);
    public T add(T t);
    public T update(T t);
    public void remove(Long id);
    
}
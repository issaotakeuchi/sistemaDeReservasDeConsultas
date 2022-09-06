package com.example.sistemaDeReservasDeConsultas.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao<T> {
    T salvar(T t);
    public List<T> buscarTodos();
    public void alterar(T t);
    public Optional<T> buscarPorId(int id);
    public void excluir(int id);
}

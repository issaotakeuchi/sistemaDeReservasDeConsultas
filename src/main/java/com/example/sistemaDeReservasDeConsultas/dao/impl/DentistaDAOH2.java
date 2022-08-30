package com.example.sistemaDeReservasDeConsultas.dao.impl;

import com.example.sistemaDeReservasDeConsultas.dao.ConfiguracaoJDBC;
import com.example.sistemaDeReservasDeConsultas.dao.IDao;
import com.example.sistemaDeReservasDeConsultas.model.Dentista;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Configuration
public class DentistaDAOH2 implements IDao<Dentista> {
    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger log = getLogger(DentistaDAOH2.class);

    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        log.info("abrindo conexao com banco");
        String sqlInsert = String.format("INSERT INTO dentista (nome, sobrenome, matriculaCadastro)" +
                "VALUES('%s', '%s', '%s')", dentista.getNome(), dentista.getSobrenome(), dentista.getMatriculaCadastro());
        Connection connection = null;

        try {
            log.info("salvando o dentista: " + dentista.getNome() + " " + dentista.getSobrenome());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/sistemaDeReservasDeConsultas;" + "INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                dentista.setId(resultSet.getInt(1));
            }
        } catch (Exception e){
            e.printStackTrace();
            log.info("erro ao inserir o dentista: " + e.getMessage());
        } finally {
            log.info("fechando a conexao");
            connection.close();
        }
        return dentista;
    }

    @Override
    public List buscarTodos() throws SQLException {
        log.info("abrindo conexao com banco");
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM dentista";
        List<Dentista> dentistas = new ArrayList<>();

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/sistemaDeReservasDeConsultas;" + "INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            log.info("buscando todos os dentistas do banco");

            while (resultSet.next()){
                dentistas.add(criarObjetoDentista(resultSet));
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } finally {
            log.info("fechando a conexao");
            statement.close();
        }
        return dentistas;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {
        String sqlUpdate = String.format("UPDATE dentista SET nome = '%s', sobrenome = '%s', matriculaCadastro = '%s' WHERE id = '%s'", dentista.getNome(), dentista.getSobrenome(), dentista.getMatriculaCadastro());
        Connection connection = null;

        try {
            log.info("alterando dados do dentista: " + dentista.getNome() + " " + dentista.getSobrenome() + " de id: " + dentista.getId());
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/sistemaDeReservasDeConsultas;" + "INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sqlUpdate);
        } catch (Exception e){
            e.printStackTrace();
            log.error("erro ao alterar o dentista " + e.getMessage());
        } finally {
            log.info("fechando conexao");
            connection.close();
        }
    }

    @Override
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        log.info("abrindo conexao com banco");
        Connection connection = null;
        Statement statement = null;
        String query = String.format("SELECT * FROM dentista WHERE id = '%s'", id);
        Dentista dentista = null;

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/sistemaDeReservasDeConsultas;" + "INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("buscando dentista por id: " + id);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                dentista = criarObjetoDentista(resultSet);
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } finally {
            log.info("fechando conexao");
            statement.close();
        }
        return dentista != null ? Optional.of(dentista) : Optional.empty();
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.info("abrindo conexao com banco");
        Connection connection = null;
        Statement statement = null;
        String sqlDelete = String.format("DELETE FROM dentista WHERE id = '%s'", id);

        try {
            configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/sistemaDeReservasDeConsultas;" + "INIT=RUNSCRIPT FROM 'create.sql'","sa","");
            connection = configuracaoJDBC.getConnection();
            log.info("excluindo dentista com id: " + id);
            statement = connection.createStatement();
            statement.execute(sqlDelete);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } finally {
            log.info("fechando conexao");
            connection.close();
        }
    }

    private Dentista criarObjetoDentista(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String sobrenome = resultSet.getString("sobrenome");
        String matriculaCadastro = resultSet.getString("matriculaCadastro");
        return new Dentista(id,nome,sobrenome,matriculaCadastro);
    }
}

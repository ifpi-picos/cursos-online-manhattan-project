package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao implements Dao<Aluno>{
    private final Connection connection;

    public AlunoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Aluno> consultarTodos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT * FROM alunos";

        try (Connection connection = Conexao.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");

                Aluno aluno = new Aluno(nome, id, email);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar alunos", e);
        }
        return alunos;
    }
}
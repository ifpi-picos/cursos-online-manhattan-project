package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements Dao<Professor> {
    private final Connection connection;

    public ProfessorDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Professor> consultarTodos() {
        List<Professor> professores = new ArrayList<>();

        String sql = "SELECT * FROM professores";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");

                Professor professor = new Professor(id, nome, email);
                professores.add(professor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar professores", e);
        }

        return professores;
    }
}
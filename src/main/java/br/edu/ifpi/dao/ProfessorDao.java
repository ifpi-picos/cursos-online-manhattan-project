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

    @Override
    public int cadastrar(Professor professor) {
        String sql = "INSERT INTO professores (nome, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, professor.getNome());
            statement.setString(2, professor.getEmail());

            int rowsAffected = statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                professor.setId(rs.getInt(1));
            }

            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar professor", e);
        }
    }

    @Override
    public int alterar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, professor.getNome());
            statement.setString(2, professor.getEmail());
            statement.setInt(3, professor.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar professor", e);
        }
    }

    @Override
    public int remover(Professor professor) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, professor.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover professor", e);
        }
    }
}
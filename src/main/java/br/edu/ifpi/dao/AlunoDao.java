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

                Aluno aluno = new Aluno(id, nome, email);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar alunos", e);
        }
        return alunos;
    }

    @Override
    public int cadastrar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getEmail());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar aluno", e);
        }
    }

    @Override
    public int alterar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, email = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getEmail());
            statement.setInt(3, aluno.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar aluno", e);
        }
    }

    @Override
    public int remover(Aluno aluno) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, aluno.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover aluno", e);
        }
    }
}
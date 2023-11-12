package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Turma;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDao implements Dao<Turma> {
    private Connection connection;

    public TurmaDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Turma> consultarTodos() {
        List<Turma> turmas = new ArrayList<>();

        try (Connection connection = Conexao.getConnection()) {
            String sql = "SELECT * FROM turmas";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    int periodo = resultSet.getInt("periodo");
                    String horario = resultSet.getString("horario");
                    
                    int professorId = resultSet.getInt("professor_id");
                    Professor professor = obterProfessorPorId(professorId);

                    Turma turma = new Turma(nome, periodo, horario, professor);
                    turmas.add(turma);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao consultar turmas", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
        return turmas;
    }

    @Override
    public int cadastrar(Turma turma) {
        String sql = "INSERT INTO turmas (nome, periodo, horario, professor_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, turma.getNome());
            statement.setInt(2, turma.getPeriodo());
            statement.setString(3, turma.getHorario());
            statement.setInt(4, turma.getProfessor().getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar turma", e);
        }
    }

    @Override
    public int alterar(Turma turma) {
        String sql = "UPDATE turmas SET nome = ?, periodo = ?, horario = ?, professor_id = ? WHERE id = ?";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, turma.getNome());
            statement.setInt(2, turma.getPeriodo());
            statement.setString(3, turma.getHorario());
            statement.setInt(4, turma.getProfessor().getId());
            statement.setInt(5, turma.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar turma", e);
        }
    }

    @Override
    public int remover(Turma turma) {
        String sql = "DELETE FROM turmas WHERE id = ?";

        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, turma.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover turma", e);
        }
    }

    // Método privado para obter um professor por ID
    private Professor obterProfessorPorId(int professorId) {
        String sql = "SELECT * FROM professores WHERE id = ?";
    
        try (Connection connection = Conexao.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, professorId);
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String email = resultSet.getString("email");
    
                    // Instância de Professor com as informações obtidas do banco de dados
                    return new Professor(professorId, nome, email);
                } else {
                    throw new RuntimeException("Professor não encontrado com o ID: " + professorId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter professor por ID", e);
        }
    }
}
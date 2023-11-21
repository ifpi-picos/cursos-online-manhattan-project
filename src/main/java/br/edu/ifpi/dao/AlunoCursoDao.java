package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;


public class AlunoCursoDao{
    private Connection connection;

    public AlunoCursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(AlunoCurso alunoCurso) {
        String sql = "INSERT INTO aluno_curso (id_aluno, id_curso) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, alunoCurso.getAluno().getId());
            statement.setInt(2, alunoCurso.getCurso().getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AlunoCurso> consultarTodos() {
        String sql = "SELECT * FROM aluno_curso";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<AlunoCurso> alunoCursos = new ArrayList<>();
            while (resultSet.next()) {
                AlunoCurso alunoCurso = new AlunoCurso(resultSet.getInt("id"), resultSet.getInt("id_aluno"), resultSet.getInt("id_curso"));
                alunoCursos.add(alunoCurso);
            }
            return alunoCursos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int alterar(AlunoCurso alunoCurso) {
        String sql = "UPDATE aluno_curso SET id_aluno = ?, id_curso = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, alunoCurso.getAluno().getId());
            statement.setInt(2, alunoCurso.getCurso().getId());
            statement.setInt(3, alunoCurso.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int remover(AlunoCurso alunoCurso) {
        String sql = "DELETE aluno_curso WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, alunoCurso.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AlunoCurso> buscarPorId(int id) {
        String sql = "SELECT * FROM aluno_curso WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<AlunoCurso> alunoCursos = new ArrayList<>();
            while (resultSet.next()) {
                AlunoCurso alunoCurso = new AlunoCurso(resultSet.getInt("id"), resultSet.getInt("id_aluno"), resultSet.getInt("id_curso"));
                alunoCursos.add(alunoCurso);
            }
            return alunoCursos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
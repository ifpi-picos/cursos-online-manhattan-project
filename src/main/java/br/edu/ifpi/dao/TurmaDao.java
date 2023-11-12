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
}
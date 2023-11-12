package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Disciplina;
import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDao implements Dao<Disciplina> {
    private final Connection connection;

    public DisciplinaDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Disciplina> consultarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();

        String sql = "SELECT * FROM disciplinas";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int cargaHoraria = resultSet.getInt("carga_horaria");
                String ementa = resultSet.getString("ementa");
                int professorId = resultSet.getInt("professor_id");

                Professor professor = obterProfessorPorId(professorId);

                Disciplina disciplina = new Disciplina(nome, cargaHoraria, ementa, professor);
                disciplina.setId(id);
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar disciplinas", e);
        }

        return disciplinas;
    }
}
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

    @Override
    public int cadastrar(Disciplina disciplina) {
        String sql = "INSERT INTO disciplinas (nome, carga_horaria, ementa, professor_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, disciplina.getNome());
            statement.setInt(2, disciplina.getCargaHoraria());
            statement.setString(3, disciplina.getEmenta());
            statement.setInt(4, disciplina.getProfessor().getId());

            int rowsAffected = statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                disciplina.setId(rs.getInt(1));
            }

            return rowsAffected;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar disciplina", e);
        }
    }

    @Override
    public int alterar(Disciplina disciplina) {
        String sql = "UPDATE disciplinas SET nome = ?, carga_horaria = ?, ementa = ?, professor_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, disciplina.getNome());
            statement.setInt(2, disciplina.getCargaHoraria());
            statement.setString(3, disciplina.getEmenta());
            statement.setInt(4, disciplina.getProfessor().getId());
            statement.setInt(5, disciplina.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar disciplina", e);
        }
    }

    @Override
    public int remover(Disciplina disciplina) {
        String sql = "DELETE FROM disciplinas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, disciplina.getId());

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover disciplina", e);
        }
    }
}
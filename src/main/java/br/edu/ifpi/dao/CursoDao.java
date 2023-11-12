package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao implements Dao<Curso> {
    private final Connection connection;

    public CursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, status, carga_horaria, descricao, professor_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setBoolean(2, curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setString(4, curso.getDescricao());

            // Certifique-se de que o professor não é nulo antes de acessar o ID
            Professor professor = curso.getProfessor();
            if (professor != null) {
                stmt.setInt(5, professor.getId());
            } else {
                throw new RuntimeException("O professor associado ao curso é nulo");
            }

            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                curso.setId(rs.getInt(1));
            }
            return curso.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int remover(Curso curso) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, curso.getId());
            stmt.execute();
            return curso.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int alterar(Curso curso) {
        String sql = "UPDATE cursos SET nome = ?, status = ?, carga_horaria = ?, descricao = ?, professor_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setBoolean(2, curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setString(4, curso.getDescricao());
            stmt.setInt(5, curso.getProfessor().getId());
            stmt.setInt(6, curso.getId());

            stmt.execute();
            return curso.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> consultarTodos() {
        String sql = "SELECT * FROM cursos";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Curso> cursos = new ArrayList<>();

            // Cria uma instância de ProfessorDao usando a conexão existente
            ProfessorDao professorDao = new ProfessorDao(connection);

            while (rs.next()) {
                String nome = rs.getString("nome");
                boolean status = rs.getBoolean("status");
                int cargaHoraria = rs.getInt("carga_horaria");
                String descricao = rs.getString("descricao");

                // Recupera o professor associado ao curso
                int professorId = rs.getInt("professor_id");
                Professor professor = professorDao.obterProfessorPorId(professorId);

                // Cria a instância de Curso com o professor recuperado
                Curso curso = new Curso(nome, status, cargaHoraria, descricao, professor);
                cursos.add(curso);
            }

            return cursos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
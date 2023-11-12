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
}
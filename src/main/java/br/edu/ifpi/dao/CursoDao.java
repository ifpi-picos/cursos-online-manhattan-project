package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Curso;

public class CursoDao implements Dao<Curso> {
    private Connection connection;

    public CursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Curso curso) {
        String sql = "INSERT INTO curso (nome, descricao) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());

            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int remover(Curso curso) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, curso.getId());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    @Override
    public int alterar(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, descricao = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setLong(3, curso.getId());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    @Override
    public List<Curso> consultarTodos() {
        String sql = "SELECT * FROM curso";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            List<Curso> cursos = new ArrayList<>();

            while (rs.next()) {
                Curso curso = new Curso(sql, 0, false, 0, sql, null);
                curso.setNome(rs.getString("nome"));
                curso.setId(rs.getInt("id"));
                curso.setStatus(rs.getBoolean("status"));
                curso.setCargaHoraria(rs.getInt("carga_horaria"));
                curso.setDescricao(rs.getString("descricao"));
                curso.setProfessor(new ProfessorDao(connection).buscaPorId(rs.getLong("professor_id")));
                cursos.add(curso);
            }

            return cursos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* @Override
    public Curso buscaPorId(Long id) {
        String sql = "SELECT * FROM curso WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getLong("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDescricao(rs.getString("descricao"));

                return curso;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } */
}
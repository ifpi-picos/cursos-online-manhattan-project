package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusCurso;

public class CursoDao implements Dao<Curso>{
    private Connection connection;

    public CursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, carga_horaria, id_professor, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setInt(3, curso.getProfessor().getId());
            stmt.setString(4, curso.getStatus().toString());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir curso no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public List<Curso> consultarTodos() {
        List<Curso> cursos = new ArrayList<>();

        String sql = "SELECT * FROM cursos";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessor = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));
                Professor professor = new ProfessorDao(connection).buscarPorId(idProfessor);

                Curso curso = new Curso(id, nome, cargaHoraria, professor, status);
                cursos.add(curso);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar cursos no banco de dados: " + e.getMessage());
        }
        return cursos;
    }

    @Override
    public int alterar(Curso curso){
        String sql = "UPDATE cursos SET nome = ?, carga_horaria = ?, professor = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setString(3, curso.getProfessor().getNome());
            stmt.setString(4, curso.getStatus().toString());
            stmt.setInt(5, curso.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar curso no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public int remover(Curso curso) {
        String sql = "DELETE FROM cursos WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, curso.getId());

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover curso no banco de dados: " + e.getMessage());
        }
    }
}
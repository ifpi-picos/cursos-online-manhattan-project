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
    
        String sql = "SELECT c.id, c.nome, c.carga_horaria, c.id_professor, c.status, p.nome as nome_professor, p.email "
                + "FROM cursos c "
                + "JOIN professores p ON c.id_professor = p.id";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessor = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));
                String nomeProfessor = rs.getString("nome_professor");
                String emailProfessor = rs.getString("email");
    
                Professor professor = new Professor(idProfessor, nomeProfessor, emailProfessor);
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

    // Método que retorna uma lista de todos os cursos com status ABERTO
    public List<Curso> consultarCursosAbertos() {
        List<Curso> cursos = new ArrayList<>();
    
        String sql = "SELECT c.id, c.nome, c.carga_horaria, c.id_professor, c.status, p.nome as nome_professor, p.email "
                + "FROM cursos c "
                + "JOIN professores p ON c.id_professor = p.id "
                + "WHERE c.status = 'ABERTO'";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessor = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));
                String nomeProfessor = rs.getString("nome_professor");
                String emailProfessor = rs.getString("email");
    
                Professor professor = new Professor(idProfessor, nomeProfessor, emailProfessor);
                Curso curso = new Curso(id, nome, cargaHoraria, professor, status);
                cursos.add(curso);
            }
    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar cursos no banco de dados: " + e.getMessage());
        }
        return cursos;
    }

    // Método que retorna uma lista dos cursos que o aluno está matriculado
    public List<Curso> consultarCursosMatriculados(int idAluno) {
        List<Curso> cursos = new ArrayList<>();
    
        String sql = "SELECT c.id, c.nome, c.carga_horaria, c.id_professor, c.status, p.nome as nome_professor, p.email "
                + "FROM cursos c "
                + "JOIN professores p ON c.id_professor = p.id "
                + "JOIN aluno_curso ac ON c.id = ac.id_curso "
                + "WHERE ac.id_aluno = ?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessor = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));
                String nomeProfessor = rs.getString("nome_professor");
                String emailProfessor = rs.getString("email");
    
                Professor professor = new Professor(idProfessor, nomeProfessor, emailProfessor);
                Curso curso = new Curso(id, nome, cargaHoraria, professor, status);
                cursos.add(curso);
            }
    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar cursos no banco de dados: " + e.getMessage());
        }
        return cursos;
    }

    public List<Curso> consultarCursosPorProfessor(int idProfessor) {
        List<Curso> cursos = new ArrayList<>();
    
        String sql = "SELECT c.id, c.nome, c.carga_horaria, c.id_professor, c.status, p.nome as nome_professor, p.email "
                + "FROM cursos c "
                + "JOIN professores p ON c.id_professor = p.id "
                + "WHERE c.id_professor = ?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idProfessor);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessorCurso = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));
                String nomeProfessor = rs.getString("nome_professor");
                String emailProfessor = rs.getString("email");
    
                Professor professor = new Professor(idProfessorCurso, nomeProfessor, emailProfessor);
                Curso curso = new Curso(id, nome, cargaHoraria, professor, status);
                cursos.add(curso);
            }
    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar cursos no banco de dados: " + e.getMessage());
        }
        return cursos;
    }

    // Método para mudar status do curso para FECHADO
    public int fecharCurso(int idCurso) {
        String sql = "UPDATE cursos SET status = 'FECHADO' WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar curso no banco de dados: " + e.getMessage());
        }
    }
}
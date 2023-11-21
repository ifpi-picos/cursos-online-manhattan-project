package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;


public class AlunoCurso{
    private Connection connection;

    public AlunoCurso(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Aluno aluno, Curso curso) {
        String sql = "INSERT INTO Aluno_Curso (id_aluno, id_curso) VALUES (?, ?)"; 

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, curso.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir Aluno_Curso no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public List<AlunoCurso> consultarTodos(Aluno aluno, Curso curso){
        String sql = "SELECT * FROM Aluno_Curso WHERE id_aluno = ? AND id_curso = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, curso.getId());
            ResultSet rs = stmt.executeQuery();
            List<AlunoCurso> alunoCurso = new ArrayList<>();

            while (rs.next()) {
                AlunoCurso ac = new AlunoCurso();
                ac.setId(rs.getInt("id"));
                ac.setIdAluno(rs.getInt("id_aluno"));
                ac.setIdCurso(rs.getInt("id_curso"));
                alunoCurso.add(ac);
            }
            return alunoCurso;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar Aluno_Curso no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}
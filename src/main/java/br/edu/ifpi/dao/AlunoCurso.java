package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;

public class AlunoCurso{
    private Connection connection;

    public AlunoCurso(Connection connection) {
        this.connection = connection;
    }

    public int cadastrar(Aluno aluno, Curso curso) {
        String sql = "INSERT INTO Aluno_Curso (id_aluno, id_curso) VALUES (?, ?)"; 

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getId());
            stmt.setInt(2, curso.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir Aluno_Curso no banco de dados: " + e.getMessage());
        }
    }
}
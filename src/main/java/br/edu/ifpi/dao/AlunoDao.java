package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import br.edu.ifpi.entities.Aluno;

public class AlunoDao implements Dao<Aluno>{
    private Connection  connection;

    public AlunoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email) VALUES (?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir aluno no banco de dados: " + e.getMessage());
        } finally {
            connection.close();
        }
    }
}

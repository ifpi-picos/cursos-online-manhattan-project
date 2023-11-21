package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.enums.StatusAluno;

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

    @Override
    public List<Aluno> consultarTodos() {
        List<Aluno> alunos = new ArrayList<>();

        String sql = "SELECT * FROM alunos";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                StatusAluno status = StatusAluno.valueOf(rs.getString("status")); // Converte a String para o Enum

                Aluno aluno = new Aluno(nome, email, status);
                alunos.add(aluno);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar alunos no banco de dados: " + e.getMessage());
        } finally {
            connection.close();
        }
        return alunos;
    }
}

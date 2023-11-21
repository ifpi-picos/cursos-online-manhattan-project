package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.enums.StatusAluno;


public class AlunoDao implements Dao<Aluno>{
    private Connection connection;

    private Connection connection;

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
        }
        return alunos;
    }

    @Override
    public int alterar(Aluno aluno){
        String sql = "UPDATE alunos SET nome = ?, email = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3,aluno.getStatus().toString()); // Converte o Enum para String

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar aluno no banco de dados: " + e.getMessage());
        }
    }

    @Override
    public int remover(Aluno aluno) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getId());

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover aluno no banco de dados: " + e.getMessage());
        }
    }
}
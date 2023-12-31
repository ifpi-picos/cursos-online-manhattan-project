package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entities.Aluno;


public class AlunoDao implements Dao<Aluno>{
    
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
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

                Aluno aluno = new Aluno(nome, email);
                alunos.add(aluno);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar alunos no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
        return alunos;
    }

    @Override
    public int alterar(Aluno aluno){
        String sql = "UPDATE alunos SET nome = ?, email = ?WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar aluno no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
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
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT COUNT(*) FROM alunos WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar email no banco de dados: " + e.getMessage());
        }
    }
    
    public Aluno buscarPorNomeEEmail(String nome, String email) {
    String sql = "SELECT * FROM alunos WHERE nome = ? AND email = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nomeDoBanco = rs.getString("nome");
            String emailDoBanco = rs.getString("email");

            Aluno aluno = new Aluno(id, nomeDoBanco, emailDoBanco);
            return aluno;
        }
        return null;
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar aluno por nome e email no banco de dados: " + e.getMessage());
    }
}
}
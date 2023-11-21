package br.edu.ifpi.dao;
import br.edu.ifpi.entities.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfessorDao implements Dao<Professor> {
    private Connection connection;

    public ProfessorDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Professor professor) {
        String sql = "INSERT INTO professores (nome, email) VALUES (?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir professor no banco de dados: " + e.getMessage());
        } finally {
            connection.close();
        }
    }

    @Override
    public int atualizar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setInt(3, professor.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar professor no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    @Override
    public int deletar(Professor professor) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar professor no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }
}

package br.edu.ifpi.dao;
import br.edu.ifpi.entities.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

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
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Professor> consultarTodos() {
        List<Professor> professores = new ArrayList<>();

        String sql = "SELECT * FROM professores";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Professor professor = new Professor(nome, email);
                professores.add(professor);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar professores no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
        return professores;
    }

    @Override
    public int alterar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, email = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setInt(3, professor.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar professor no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    @Override
    public int remover(Professor professor) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, professor.getId());

            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover professor no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    // Buscar professor por id
    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professores WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Professor professor = new Professor(nome, email);
                return professor;
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar professor no banco de dados: " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
            }
        }
        return null;
    }
}
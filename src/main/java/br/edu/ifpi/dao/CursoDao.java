package br.edu.ifpi.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CursoDao implements Dao<Curso>{
    private Connection connection;

    public CursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(Curso curso) {
        String sql = "INSERT INTO cursos (nome, descricao) VALUES (?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            return stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir curso no banco de dados: " + e.getMessage());
        }
    }

    

package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.AlunoCurso;

public class AlunoCursoDao implements Dao<AlunoCurso>{
    private Connection connection;

    public AlunoCursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(AlunoCurso alunoCurso) {
        String sql = "INSERT INTO alunos_cursos (id_aluno, id_curso) VALUES (?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, alunoCurso.getAluno().getId());
            statement.setInt(2, alunoCurso.getCurso().getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<AlunoCurso> consultarTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public int alterar(AlunoCurso entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public int remover(AlunoCurso entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

}
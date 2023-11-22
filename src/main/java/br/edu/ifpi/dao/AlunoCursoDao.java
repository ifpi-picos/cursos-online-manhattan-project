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
        String sql = "INSERT INTO Aluno_Curso (id_aluno, id_curso) VALUES (?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, alunoCurso.getAluno().getId());
            stmt.setInt(2, alunoCurso.getCurso().getId());
            return stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AlunoCurso> consultarTodos() {
        String sql = "SELECT * FROM Aluno_Curso";
        List<AlunoCurso> alunosCursos = new ArrayList<AlunoCurso>();


            while (resultSet.next()) {
                Aluno aluno = new AlunoDao(connection).consultarPorId(resultSet.getInt("id_aluno"));
                Curso curso = new CursoDao(connection).consultarPorId(resultSet.getInt("id_curso"));
                AlunoCurso alunoCurso = new AlunoCurso(aluno, curso);
                alunosCursos.add(alunoCurso);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunosCursos;
    }

}
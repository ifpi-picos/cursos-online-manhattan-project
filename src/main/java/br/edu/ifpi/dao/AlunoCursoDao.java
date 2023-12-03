package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusAlunoCurso;
import br.edu.ifpi.enums.StatusCurso;

public class AlunoCursoDao implements Dao<AlunoCurso>{
    private Connection connection;

    public AlunoCursoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int cadastrar(AlunoCurso alunoCurso) {
        String sql = "INSERT INTO aluno_curso (id_aluno, id_curso) VALUES (?, ?)";

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
        // String sql = "SELECT * FROM aluno_curso";
        List<AlunoCurso> alunosCursos = new ArrayList<AlunoCurso>();
        return alunosCursos;
    }

    @Override
    public int alterar(AlunoCurso entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public int remover(AlunoCurso entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
    
    // Lista de cursos que estão com o status ABERTO e que gere um objeto Aluno, um objeto Curso e um objeto AlunoCurso
    public List<AlunoCurso> consultarCursosAbertosParaAluno(int idAluno) {
        List<AlunoCurso> alunosCursos = new ArrayList<>();
        String sql = "SELECT " +
                "alunos.id AS aluno_id, alunos.nome AS aluno_nome, alunos.email AS aluno_email, " +
                "cursos.id AS curso_id, cursos.nome AS curso_nome, cursos.carga_Horaria AS carga_horaria, " +
                "professores.id AS professor_id, professores.nome AS professor_nome, professores.email AS professor_email, " +
                "aluno_curso.nota1, aluno_curso.nota2, aluno_curso.nota3, aluno_curso.media, aluno_curso.status_matricula " +
                "FROM aluno_curso " +
                "INNER JOIN alunos ON aluno_curso.aluno_id = alunos.id " +
                "INNER JOIN cursos ON aluno_curso.curso_id = cursos.id " +
                "INNER JOIN professores ON cursos.id_professor = professores.id " +
                "WHERE cursos.status = 'ABERTO' AND alunos.id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);  // Define o ID do aluno no PreparedStatement
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("aluno_id"), rs.getString("aluno_nome"), rs.getString("aluno_email"));
                Professor professor = new Professor(rs.getInt("professor_id"), rs.getString("professor_nome"), rs.getString("professor_email"));
                Curso curso = new Curso(rs.getInt("curso_id"), rs.getString("curso_nome"), rs.getInt("carga_horaria"), professor, StatusCurso.ABERTO);
    
                Double nota1 = rs.getDouble("nota1");
                Double nota2 = rs.getDouble("nota2");
                Double nota3 = rs.getDouble("nota3");
                Double media = rs.getDouble("media");
                StatusAlunoCurso statusAlunoCurso = StatusAlunoCurso.valueOf(rs.getString("status_matricula"));
    
                AlunoCurso alunoCurso = new AlunoCurso(aluno, curso, nota1, nota2, nota3, media, statusAlunoCurso);
                alunosCursos.add(alunoCurso);
            }
    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cursos abertos para o aluno no banco de dados: " + e.getMessage(), e);
        }
        return alunosCursos;
    }
    
}
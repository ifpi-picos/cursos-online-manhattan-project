package br.edu.ifpi.dao;

import java.io.ObjectInputFilter.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

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
    public List<AlunoCurso> consultarCursosAbertos() {
        List<AlunoCurso> alunosCursos = new ArrayList<AlunoCurso>();
        String sql = "SELECT * FROM aluno_curso " +
             "INNER JOIN alunos ON aluno_curso.id_aluno = alunos.id " +
             "INNER JOIN cursos ON aluno_curso.id_curso = cursos.id " +
             "INNER JOIN professores ON cursos.id_professor = professores.id " +
             "WHERE cursos.status = 'ABERTO'";


        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                int idAluno = rs.getInt("alunos.id");
                String nomeAluno = rs.getString("alunos.nome");
                String emailAluno = rs.getString("alunos.email");
                Aluno aluno = new Aluno(idAluno, nomeAluno, emailAluno);

                int idProfessor = rs.getInt("cursos.id_professor");
                String nomeProfessor = rs.getString("professores.nome");
                String emailProfessor = rs.getString("professores.email");
                Professor professor = new Professor(idProfessor, nomeProfessor, emailProfessor);
                
                int idCurso = rs.getInt("cursos.id");
                String nomeCurso = rs.getString("cursos.nome");
                int cargaHoraria = rs.getInt("cursos.carga_horaria");
                StatusCurso statusCurso = StatusCurso.valueOf(rs.getString("cursos.status"));
                Curso curso = new Curso(idCurso, nomeCurso, cargaHoraria, professor, statusCurso);
                
                Double nota1 = rs.getDouble("aluno_curso.nota1");
                Double nota2 = rs.getDouble("aluno_curso.nota2");
                Double nota3 = rs.getDouble("aluno_curso.nota3");
                Double media = rs.getDouble("aluno_curso.media");
                StatusAlunoCurso statusAlunoCurso = StatusAlunoCurso.valueOf(rs.getString("aluno_curso.status_matricula"));
                AlunoCurso alunoCurso = new AlunoCurso(aluno, curso, nota1, nota2, nota3, media, statusAlunoCurso);
                alunosCursos.add(alunoCurso);  
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar alunos no banco de dados: " + e.getMessage());
        }
        return alunosCursos;
    }

    // Método para calcular a média geral do curso
    public double calcularMediaGeralDoCurso() {
        List<AlunoCurso> alunosCursos = consultarCursosAbertos();
    
        if (alunosCursos.isEmpty()) {
            // Retornar 0 ou outro valor padrão caso não haja alunos matriculados
            return 0.0;
        }
    
        double somaMedias = 0.0;
        int quantidadeAlunos = 0;
    
        for (AlunoCurso alunoCurso : alunosCursos) {
            // Somar as médias de cada aluno
            somaMedias += alunoCurso.getMedia();
            quantidadeAlunos++;
        }
    
        // Calcular a média geral do curso
        double mediaGeral = somaMedias / quantidadeAlunos;
    
        return mediaGeral;
    }    
    
}
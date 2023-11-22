package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusAluno;
import br.edu.ifpi.enums.StatusCurso;
import br.edu.ifpi.entities.AlunoCurso;

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
        String sql = "SELECT * FROM aluno_curso";
        List<AlunoCurso> alunosCursos = new ArrayList<AlunoCurso>();
        return alunosCursos;
    }

    public List<Aluno> consultarAlunosPorCurso(int idCurso) {
        String sql = "SELECT * FROM alunos " +
                    "WHERE id IN (SELECT id_aluno FROM aluno_curso WHERE id_curso = ?)";
        List<Aluno> alunosAssociados = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                StatusAluno status = StatusAluno.valueOf(rs.getString("status"));

                Aluno aluno = new Aluno(id, nome, email, status);
                alunosAssociados.add(aluno);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunosAssociados;
    }

    public List<Curso> consultarCursosPorAluno(int idAluno) {
        String sql = "SELECT * FROM cursos " +
                    "WHERE id IN (SELECT id_curso FROM aluno_curso WHERE id_aluno = ?)";
        List<Curso> cursosAssociados = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cargaHoraria = rs.getInt("carga_horaria");
                int idProfessor = rs.getInt("id_professor");
                StatusCurso status = StatusCurso.valueOf(rs.getString("status"));

                Professor professor = new ProfessorDao(connection).buscarPorId(idProfessor);

                Curso curso = new Curso(id, nome, cargaHoraria, professor, status);
                cursosAssociados.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cursosAssociados;
    }

    // Cursos que o aluno não está matriculado
    public List<String> consultarCursosNaoMatriculados(int id) {
        List<String> cursos = new ArrayList<>();

        String sql = "SELECT id_curso FROM aluno_curso WHERE id_aluno != ? ORDER BY";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String nome = rs.getString("nome");
                cursos.add(nome);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar cursos não matriculados no banco de dados: " + e.getMessage());
        } 
        return cursos;
    }


    @Override
        public int alterar(AlunoCurso entidade) {
            throw new UnsupportedOperationException("Unimplemented method 'alterar'");
        }

        @Override
        public int remover(AlunoCurso entidade) {
            throw new UnsupportedOperationException("Unimplemented method 'remover'");
        }

        // Método para fazer o cadastro de notas em um array no banco de dados
        public int cadastrarNotas(AlunoCurso alunoCurso) {
            String sql = "INSERT INTO notas (id_aluno, id_curso, nota1, nota2, nota3) VALUES (?, ?, ?, ?, ?)";
        
            try {
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, alunoCurso.getAluno().getId());
                stmt.setInt(2, alunoCurso.getCurso().getId());
                
                Double[] notas = alunoCurso.getNota();
                stmt.setDouble(3, notas[0]); // supondo que a nota1 está na posição 0 do array
                stmt.setDouble(4, notas[1]); // supondo que a nota2 está na posição 1 do array
                stmt.setDouble(5, notas[2]); // supondo que a nota3 está na posição 2 do array
        
                return stmt.executeUpdate();
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }        
    }
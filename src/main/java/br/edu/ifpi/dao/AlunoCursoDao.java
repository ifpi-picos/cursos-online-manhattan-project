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
import br.edu.ifpi.enums.StatusAluno;
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


    @Override
    public int alterar(AlunoCurso entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public int remover(AlunoCurso entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    public int cadastrarNotas(AlunoCurso alunoCurso) {
        String sql = "UPDATE aluno_curso SET nota1 = ?, nota2 = ?, nota3 = ? WHERE id_aluno = ? AND id_curso = ?";
            
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setDouble(1, alunoCurso.getNota1());
                stmt.setDouble(2, alunoCurso.getNota2());
                stmt.setDouble(3, alunoCurso.getNota3());
                stmt.setInt(4, alunoCurso.getAluno().getId());
                stmt.setInt(5, alunoCurso.getCurso().getId());
                
                return stmt.executeUpdate();
                
                
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }    
        
    public List<Curso> consultarCursosNaoMatriculados(int idAluno) {
        List<Curso> cursosNaoMatriculados = new ArrayList<>();

        String sql = "SELECT * FROM cursos WHERE id NOT IN (SELECT id_curso FROM aluno_curso WHERE id_aluno = ?)";

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
                cursosNaoMatriculados.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cursos não matriculados no banco de dados: " + e.getMessage());
        }
        return cursosNaoMatriculados;
    }

    public double[] consultarNotaPorAlunoECurso(int idAluno, int idCurso) {
        String sql = "SELECT nota1, nota2, nota3 FROM aluno_curso WHERE id_aluno = ? AND id_curso = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                double nota1 = rs.getDouble("nota1");
                double nota2 = rs.getDouble("nota2");
                double nota3 = rs.getDouble("nota3");
    
                return new double[]{nota1, nota2, nota3};
            }
    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar nota no banco de dados: " + e.getMessage());
        }
    
        // Caso não encontre nota para o aluno e curso especificados, você pode retornar um valor padrão ou lançar uma exceção, dependendo da sua lógica.
        throw new RuntimeException("Nota não encontrada para o aluno e curso especificados");
    }

    // Função p/ exibir a quantidade de alunos matriculados em um curso
    public int quantidadeAlunosMatriculados(int idCurso) {
        // SELECT COUNT(*) AS total conta o número de linhas retornadas pela consulta
        String sql = "SELECT COUNT(*) AS total FROM aluno_curso WHERE id_curso = ? AND status = 'MATRICULADO'";
        int totalAlunos = 0; // Variável para armazenar o total de alunos baseados nas linhas retornadas pela consulta
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                totalAlunos = rs.getInt("total");
            } else {
                throw new RuntimeException("Erro ao consultar a quantidade de alunos matriculados no curso.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar a quantidade de alunos matriculados no curso: " + e.getMessage());
        }
        return totalAlunos;
    }
    
    // Funções p/ calcular a média geral e porcentagem de alunos aprovados em um curso
    public double calcularMediaGeral(int idCurso) {
        String sql = "SELECT AVG(media) AS media_geral FROM aluno_curso WHERE id_curso = ?";
        double mediaGeral = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mediaGeral = rs.getDouble("media_geral");
            } else {
                throw new RuntimeException("Erro ao calcular a média geral dos alunos.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular a média geral dos alunos: " + e.getMessage());
        }
        return mediaGeral;
    }

    public double calcularPorcentagemAprovados(int idCurso) {
        String sql = "SELECT COUNT(*) AS total_aprovados FROM aluno_curso WHERE id_curso = ? AND media >= 7";
        int totalAprovados = 0;
        int totalAlunos = 0;
        double porcentagemAprovados = 0;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalAprovados = rs.getInt("total_aprovados");
            } else {
                throw new RuntimeException("Erro ao calcular a porcentagem de alunos aprovados.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular a porcentagem de alunos aprovados: " + e.getMessage());
        }

        totalAlunos = quantidadeAlunosMatriculados(idCurso);
        if (totalAlunos > 0) {
            porcentagemAprovados = (double) totalAprovados / totalAlunos * 100;
        }

        return porcentagemAprovados;
    }
    
}
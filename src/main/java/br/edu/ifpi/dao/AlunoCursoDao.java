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
        List<AlunoCurso> alunosCursos = new ArrayList<>();
        String sql = "SELECT ac.*, a.id AS aluno_id, a.nome AS aluno_nome, a.email AS aluno_email, " +
            "c.id AS curso_id, c.nome AS curso_nome, c.carga_horaria, " +
            "p.id AS professor_id, p.nome AS professor_nome, p.email AS professor_email " +
            "FROM Aluno_Curso ac " +
            "JOIN Alunos a ON ac.aluno_id = a.id " +
            "JOIN Cursos c ON ac.curso_id = c.id " +
            "JOIN Professores p ON c.id_professor = p.id";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getInt("aluno_id"), rs.getString("aluno_nome"), rs.getString("aluno_email"));
                Professor professor = new Professor(rs.getInt("professor_id"), rs.getString("professor_nome"), rs.getString("professor_email"));
                Curso curso = new Curso(rs.getInt("curso_id"), rs.getString("curso_nome"), rs.getInt("carga_horaria"), professor, StatusCurso.ABERTO);
                StatusAlunoCurso statusAlunoCurso = StatusAlunoCurso.valueOf(rs.getString("status_matricula"));
                
                AlunoCurso alunoCurso = new AlunoCurso(aluno, curso, statusAlunoCurso);
                alunosCursos.add(alunoCurso);
            }
            return alunosCursos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    // Método que retorna uma lista dos cursos que o aluno está matriculado
    public List<AlunoCurso> consultarCursosMatriculados(int idAluno) {
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
                "WHERE alunos.id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);  
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
            throw new RuntimeException("Erro ao consultar cursos matriculados no banco de dados: " + e.getMessage(), e);
        }
        return alunosCursos;
    }
    
    // Método para exibir a nota média geral dos alunos de um determinado curso
        public double calcularMediaGeralPorCurso(int idCurso) {
            String sql = "SELECT AVG(media) AS media_geral FROM aluno_curso " +
                         "WHERE curso_id = ?";
    
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idCurso);
                ResultSet rs = stmt.executeQuery();
    
                if (rs.next()) {
                    return rs.getDouble("media_geral");
                }
    
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao calcular média geral dos alunos no banco de dados: " + e.getMessage(), e);
            }
            return 0; // Retorna 0 se não houver registros ou ocorrer um erro
        }
    

        public double calcularPorcentagemAprovadosReprovados(int idCurso) {
            String sql = "SELECT COUNT(*) AS total_alunos, " +
                         "SUM(CASE WHEN status_matricula = ? THEN 1 ELSE 0 END) AS total_aprovados " +
                         "FROM aluno_curso WHERE curso_id = ? AND status_matricula IN (?, ?)";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, StatusAlunoCurso.APROVADO.toString());
                stmt.setInt(2, idCurso);
                stmt.setString(3, StatusAlunoCurso.APROVADO.toString());
                stmt.setString(4, StatusAlunoCurso.REPROVADO.toString());
        
                ResultSet rs = stmt.executeQuery();
        
                if (rs.next()) {
                    int totalAlunos = rs.getInt("total_alunos");
                    int totalAprovados = rs.getInt("total_aprovados");
        
                    if (totalAlunos == 0) {
                        // Retornar 0 se não houver alunos aprovados ou reprovados
                        return 0.0;
                    }
        
                    // Calcular a porcentagem de alunos aprovados
                    double porcentagemAprovados = (double) totalAprovados / totalAlunos * 100.0;
                    return porcentagemAprovados;
                }
        
                // Retornar 0 se ocorrer algum problema ou se não houver alunos no curso
                return 0.0;
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao calcular a porcentagem de alunos aprovados/reprovados no banco de dados: " + e.getMessage(), e);
            }
        }
        

    public int calcularQuantidadeAlunosAtivosNoCurso(int idCurso) {
        String sql = "SELECT COUNT(*) AS total_alunos FROM aluno_curso " +
                     "WHERE curso_id = ? AND status_matricula = 'ATIVO'";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return rs.getInt("total_alunos");
            }
    
            // Retornar 0 se não houver alunos ATIVOS no curso
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular a quantidade de alunos ativos no curso no banco de dados: " + e.getMessage(), e);
        }
    }
    
    public int calcularQuantidadeAlunosConcluidos(int idCurso) {
        String sql = "SELECT COUNT(*) AS total_alunos FROM aluno_curso " +
                     "WHERE curso_id = ? AND status_matricula IN ('APROVADO', 'REPROVADO')";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_alunos");
            }

            // Retornar 0 se não houver alunos concluídos
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao calcular a quantidade de alunos concluídos no banco de dados: " + e.getMessage(), e);
        }
    }
  
    //Consultar alunos com status Ativo e Concluído
    public List<AlunoCurso> consultarCursosMatriculadosConcluidos(int idAluno) {
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
                "WHERE alunos.id = ? AND (aluno_curso.status_matricula = 'APROVADO' OR aluno_curso.status_matricula = 'REPROVADO')";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);  
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
            throw new RuntimeException("Erro ao consultar cursos matriculados concluídos no banco de dados: " + e.getMessage(), e);
        }
        return alunosCursos;
    }
    
    //Consultar cursos com status Ativo/Cursando
    public List<AlunoCurso> consultarCursosMatriculadosCursando(int idAluno) {
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
                "WHERE alunos.id = ? AND aluno_curso.status_matricula = 'ATIVO'";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);  
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
            throw new RuntimeException("Erro ao consultar alunos matriculados cursando no banco de dados: " + e.getMessage(), e);
        }
        return alunosCursos;
    }
    
    //Consultar alunos com status Inativo/Desistiu
    public List<AlunoCurso> consultarCursosTrancados(int idAluno) {
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
                "WHERE alunos.id = ? AND aluno_curso.status_matricula = 'INATIVO'";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);  
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
            throw new RuntimeException("Erro ao consultar alunos matriculados cursando no banco de dados: " + e.getMessage(), e);
        }
        return alunosCursos;
    }
    
}
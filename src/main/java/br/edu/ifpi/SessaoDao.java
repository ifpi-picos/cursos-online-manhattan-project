package br.edu.ifpi;

import java.sql.Connection;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;

public class SessaoDao {
    private Connection connection;
    private AlunoDao alunoDao;
    private ProfessorDao professorDao;
    private CursoDao cursoDao;
    private AlunoCursoDao alunoCursoDao;

    
    public SessaoDao(Connection connection, AlunoDao alunoDao, ProfessorDao professorDao, CursoDao cursoDao, AlunoCursoDao alunoCursoDao) {
        this.connection = connection;
        this.alunoDao = alunoDao;
        this.professorDao = professorDao;
        this.cursoDao = cursoDao;
        this.alunoCursoDao = alunoCursoDao;
    }
    
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public AlunoDao getAlunoDao() {
        return alunoDao;
    }
    public void setAlunoDao(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }
    public ProfessorDao getProfessorDao() {
        return professorDao;
    }
    public void setProfessorDao(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }
    public CursoDao getCursoDao() {
        return cursoDao;
    }
    public void setCursoDao(CursoDao cursoDao) {
        this.cursoDao = cursoDao;
    }
    public AlunoCursoDao getAlunoCursoDao() {
        return alunoCursoDao;
    }
    public void setAlunoCursoDao(AlunoCursoDao alunoCursoDao) {
        this.alunoCursoDao = alunoCursoDao;
    }


}

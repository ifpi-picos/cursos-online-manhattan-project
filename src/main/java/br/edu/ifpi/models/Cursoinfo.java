package br.edu.ifpi.models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.enums.StatusAlunoCurso;

public class Cursoinfo {
    private AlunoCurso alunoCurso;
    private Integer quantAlunos;
    private Double aproveitamento;
    AlunoCursoDao alunoCursoDao;
    
    public Cursoinfo (AlunoCurso alunoCurso){
        this.alunoCurso = alunoCurso;
        try {
            this.alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void setAlunoCurso(AlunoCurso alunoCurso) {
        this.alunoCurso = alunoCurso;
    }
    
    public void setQuantAlunos() {
        this.quantAlunos = this.alunoCursoDao.calcularQuantidadeAlunosAtivosNoCurso(this.alunoCurso.getCurso().getId());
    }

    public void setAproveitamento() {
        this.aproveitamento = alunoCursoDao.calcularPorcentagemAprovadosReprovados(this.alunoCurso.getCurso().getId(), StatusAlunoCurso.APROVADO);
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public Integer getQuantAlunos() {
        return quantAlunos;
    }

    public AlunoCurso getAlunoCurso() {
        return alunoCurso;
    }

    public String getNomeCurso(){
        return this.alunoCurso.getCurso().getNome();
    }

    public String getProfessor(){
        return this.alunoCurso.getCurso().getProfessor().getNome();
    }

    public static List<Cursoinfo> gerarListaCursoinfo(List<AlunoCurso> listaAlunoCurso) {
        List<Cursoinfo> listaCursoinfo = new ArrayList<>();

        for (AlunoCurso alunoCurso : listaAlunoCurso) {
            Cursoinfo cursoinfo = new Cursoinfo(alunoCurso);
            cursoinfo.setQuantAlunos();
            cursoinfo.setAproveitamento(); 
            listaCursoinfo.add(cursoinfo);
        }

        return listaCursoinfo;
    }

}

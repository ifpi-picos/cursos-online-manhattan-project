package br.edu.ifpi.models;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.enums.StatusCurso;

public class Cursoinfo {
    private Integer quantAlunosCursando;
    private Integer quantAlunosConcluido;
    private Double aproveitamento;
    private Curso curso;
    private Double mediaGeralCurso;
    AlunoCursoDao alunoCursoDao;
    
    public Cursoinfo (){
        try {
            this.alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setAproveitamento() {
        // double aproveitamentoRaw = alunoCursoDao.calcularPorcentagemAprovadosReprovados(this.curso.getId());
        // // DecimalFormat df = new DecimalFormat("#,##");
        // this.aproveitamento = Double.parseDouble(df.format(aproveitamentoRaw));
        this.aproveitamento = alunoCursoDao.calcularPorcentagemAprovadosReprovados(this.curso.getId());
    }
    
    public void setQuantAlunosCursando() {
        this.quantAlunosCursando = this.alunoCursoDao.calcularQuantidadeAlunosAtivosNoCurso(this.curso.getId());
    }

    public void setQuantAlunosConcluido() {
        this.quantAlunosConcluido = this.alunoCursoDao.calcularQuantidadeAlunosConcluidos(this.curso.getId());
    }

    public void setMediaGeralCurso(){
        this.mediaGeralCurso = alunoCursoDao.calcularMediaGeralPorCurso(this.curso.getId());
    }

    public Integer getQuantAlunosConcluido() {
        return quantAlunosConcluido;
    }
    
    public Integer getQuantAlunosCursando() {
        return quantAlunosCursando;
    }
    
    public Double getAproveitamento() {
        return aproveitamento;
    }

    public Integer getCargaHoraria(){
        return curso.getCargaHoraria();
    }

    public String getNomeCurso(){
        return this.curso.getNome();
    }

    public String getProfessor(){
        return this.curso.getProfessor().getNome();
    }

    public Double getMediaGeralCurso() {
        DecimalFormat df = new DecimalFormat("#,##");
        return Double.parseDouble(df.format(mediaGeralCurso));
        // return mediaGeralCurso;
    }

    public StatusCurso getStatusCurso(){
        return this.curso.getStatus();
    }


    public List<Cursoinfo> gerarListaCursoinfo(List<Curso> listaCursos) {
        List<Cursoinfo> listaCursoinfo = new ArrayList<>();

        for (Curso curso : listaCursos) {
            Cursoinfo cursoinfo = new Cursoinfo();
            cursoinfo.setCurso(curso);
            cursoinfo.setQuantAlunosCursando();
            cursoinfo.setQuantAlunosConcluido();
            cursoinfo.setAproveitamento();
            cursoinfo.setMediaGeralCurso();
            listaCursoinfo.add(cursoinfo);
        }

        return listaCursoinfo;
    }
}

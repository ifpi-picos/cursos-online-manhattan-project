package br.edu.ifpi.models;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.enums.StatusAlunoCurso;

public class Cursoinfo {
    private Integer quantAlunos;
    private Double aproveitamento;
    private Curso curso;
    
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
    
    public void setQuantAlunos() {
        this.quantAlunos = this.alunoCursoDao.calcularQuantidadeAlunosAtivosNoCurso(this.curso.getId());
    }

    public void setAproveitamento() {
        double aproveitamentoRaw = alunoCursoDao.calcularPorcentagemAprovadosReprovados(this.curso.getId(), StatusAlunoCurso.APROVADO);
        DecimalFormat df = new DecimalFormat("#,##");
        this.aproveitamento = Double.parseDouble(df.format(aproveitamentoRaw));
    }

    public Double getAproveitamento() {
        return aproveitamento;
    }

    public Integer getQuantAlunos() {
        return quantAlunos;
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

    public List<Cursoinfo> gerarListaCursoinfo(List<Curso> listaCursos) {
        List<Cursoinfo> listaCursoinfo = new ArrayList<>();

        for (Curso curso : listaCursos) {
            Cursoinfo cursoinfo = new Cursoinfo();
            cursoinfo.setCurso(curso);
            cursoinfo.setQuantAlunos();
            cursoinfo.setAproveitamento();

            listaCursoinfo.add(cursoinfo);
        }

        return listaCursoinfo;
    }
}

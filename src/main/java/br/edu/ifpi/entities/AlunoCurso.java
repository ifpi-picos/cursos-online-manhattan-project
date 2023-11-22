package br.edu.ifpi.entities;

public class AlunoCurso {
    private Aluno aluno;
    private Curso curso;
    private Double[] nota;

    //construtor
    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.nota = new Double[3];
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public Double[] getNota() {
        return nota;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setNota(Double[] nota) {
        this.nota = nota;
    }

}
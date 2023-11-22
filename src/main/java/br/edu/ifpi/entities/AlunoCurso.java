package br.edu.ifpi.entities;

public class AlunoCurso {
    private String id;
    private Aluno aluno;
    private Curso curso;

    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.id = aluno.getId() + "-" + curso.getId();
    }

    public String getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
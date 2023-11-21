package br.edu.ifpi.entities;

import java.util.ArrayList;

public class AlunoCurso {
    private int id;
    private Aluno aluno;
    private Curso curso;
    private Double[] nota;

    // Construtores
    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.nota = new Double[3];
    }
    
    public AlunoCurso(int id, Aluno aluno, Curso curso) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.nota = new Double[3];
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Double[] getNota() {
        return nota;
    }

    public void setNota(Double[] nota) {
        this.nota = nota;
    }
    
    public Double getNota(int i) {
        return nota[i];
    }
    public void setNota(Double nota, int i) {
        this.nota[i] = nota;
    }
    
}


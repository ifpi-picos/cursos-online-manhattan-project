package br.edu.ifpi.entities;

import br.edu.ifpi.enums.StatusAluno;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private List<Curso> cursos; // Cursos que o aluno está matriculado
    private StatusAluno status;

    // Construtores
    public Aluno(String nome, String email, StatusAluno status) {
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
        this.status = status;
    }

    public Aluno(int id, String nome, String email, StatusAluno status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
        this.status = status;
    }

    public Aluno(String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public StatusAluno getStatus() {
        return status;
    }

    public void setStatus(StatusAluno status) {
        this.status = status;
    }
}
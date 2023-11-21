package br.edu.ifpi.entities;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private List<Curso> cursos; // Cursos que o aluno está matriculado

    // Construtores
    public Aluno(int id, String nome, String email, List<Curso> cursos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
    }

    public Aluno(String nome, String email, List<Curso> cursos) {
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
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
}
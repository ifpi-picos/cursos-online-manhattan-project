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
}
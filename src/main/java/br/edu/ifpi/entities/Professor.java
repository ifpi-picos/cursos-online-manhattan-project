package br.edu.ifpi.entities;

public class Professor {
    private int id;
    private String nome;
    private String email;
    private List<Curso> cursos; // Cursos que o professor ministra

    // Construtores
    public Professor(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
    }
}
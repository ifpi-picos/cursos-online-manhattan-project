package br.edu.ifpi.entities;

import java.util.ArrayList;
import java.util.List;

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
    // Construtor completo para manipulação no sistema
    public Professor(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
    }
    // Getters e Setters
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

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Curso curso) {
        this.cursos.add(curso) ;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
        public String toString() {
            return nome;
        }
}

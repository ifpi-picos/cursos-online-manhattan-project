package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private List<Curso> cursos; // Cursos que o aluno está matriculado
    
    // Construtores

    // Construtor sem id para facilitar a autenticação
    public Aluno(String nome, String email) {
      this.nome = nome;
      this.email = email;
      this.cursos = new ArrayList<>();
    }
  
    // Construtor completo para manipulação no sistema
    public Aluno(int id, String nome, String email) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
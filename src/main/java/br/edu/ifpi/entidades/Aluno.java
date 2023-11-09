package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private int id;
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
    public Aluno(String nome, int id, String email) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.cursos = new ArrayList<>();
    }
}

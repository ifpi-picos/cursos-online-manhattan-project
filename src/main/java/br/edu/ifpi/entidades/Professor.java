package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Professor {
  private String nome;
  private String email;
  private int id;
  private List<Curso> cursos; // Cursos que o professor ministra

  // Construtores

  // Construtor sem id para facilitar a autenticação
  public Professor(String nome, String email) {
    this.nome = nome;
    this.email = email;
    this.cursos = new ArrayList<>();
  }

  // Construtor completo para manipulação no sistema
  public Professor(String nome, String email, int id) {
    this.nome = nome;
    this.email = email;
    this.id = id;
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

  //Não é necessário get e set para id, pois ele é gerado automaticamente pelo banco de dados.

  public List<Curso> getCursos() {
      return cursos;
  }

  public void setCursos(List<Curso> cursos) {
      this.cursos = cursos;
  }
}
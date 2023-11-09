package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
  private String email;
  private int id;
  

  // Construtores

  // Construtor sem id para facilitar a autenticação
  public Professor(String nome, String email) {
    this.nome = nome;
    this.email = email;
    
  }

  // Construtor completo para manipulação no sistema
  public Professor(String nome, String email, int id) {
    this.nome = nome;
    this.email = email;
    this.id = id;
    
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
}

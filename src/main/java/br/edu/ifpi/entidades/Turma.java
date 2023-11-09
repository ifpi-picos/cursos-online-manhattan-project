package br.edu.ifpi.entidades;

public class Turma {
    private int id;
    private String nome;
    private int periodo;
    private String horario;
    private Professor professor;

    // Construtores
    
    // Construtor sem id para facilitar a autenticação
    public Turma(String nome, int periodo, String horario, Professor professor) {
        this.nome = nome;
        this.periodo = periodo;
        this.horario = horario;
        this.professor = professor;
    }

    // Construtor completo para manipulação no sistema
    public Turma(int id, String nome, int periodo, String horario, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.horario = horario;
        this.professor = professor;
    }

    // Getters e Setters
    // Não é necessário get e set para id, pois ele é gerado automaticamente pelo banco de dados.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
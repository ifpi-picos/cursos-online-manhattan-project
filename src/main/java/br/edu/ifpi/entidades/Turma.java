package br.edu.ifpi.entidades;

public class Turma {
    private int id;
    private String nome;
    private int periodo;
    private String horario;
    private Professor professor;

    // Construtor
    public Turma(int id, String nome, int periodo, String horario, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.periodo = periodo;
        this.horario = horario;
        this.professor = professor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

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
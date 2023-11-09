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
}

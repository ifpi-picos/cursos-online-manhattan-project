package br.edu.ifpi.entidades;

public class Disciplina {
    private String nome;
    private int id;
    private int cargaHoraria;
    private String ementa;
    private Professor professor;

    // Construtor
    public Disciplina(String nome, int cargaHoraria, String ementa, Professor professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
        this.professor = professor;
    }
}

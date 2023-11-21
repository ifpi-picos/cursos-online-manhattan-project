package br.edu.ifpi.entities;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {
    private int id;
    private String nome;
    private StatusCurso status;
    private int cargaHoraria;
    private Professor professor;
    private List<Double> notas;

    // Construtores
    public Curso(String nome, int cargaHoraria, Professor professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.status = StatusCurso.ATIVO;
        this.notas = new ArrayList<>();
    }

    // Construtor completo para manipulação no sistema
    public Curso(int id, String nome, StatusCurso status, int cargaHoraria, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.notas = new ArrayList<>();
    }

    // Getters e Setters
}

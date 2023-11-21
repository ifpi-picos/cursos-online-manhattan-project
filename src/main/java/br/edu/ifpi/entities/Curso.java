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
}

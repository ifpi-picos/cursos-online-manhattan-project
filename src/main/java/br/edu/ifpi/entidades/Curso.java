package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nome;
    private int id;
    private boolean status;
    private int cargaHoraria;
    private String descricao;
    private Professor professor;
    private List<Double> notas;


    // Construtor
    public Curso(String nome, int id, boolean status, int cargaHoraria, String descricao, Professor professor) {
        this.nome = nome;
        this.id = id;
        this.status = status;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.notas = new ArrayList<>();
    }
}

package br.edu.ifpi.entidades;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private int id;
    private String nome;
    private int periodo;
    private List<Aluno> alunos;
    private String horario;
    private Professor professor;

    // Construtor
    public Turma(String nome, int periodo, String horario, Professor professor) {
        this.nome = nome;
        this.periodo = periodo;
        this.alunos = new ArrayList<>();
        this.horario = horario;
        this.professor = professor;
    }
}

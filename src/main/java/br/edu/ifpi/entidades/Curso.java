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

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Não é necessário get e set para id, pois ele é gerado automaticamente pelo banco de dados.

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public void setNotas(List<Double> notas) {
        this.notas = notas;
    }
}
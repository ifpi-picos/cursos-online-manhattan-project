package br.edu.ifpi.entities;

import br.edu.ifpi.enums.StatusCurso;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String nome;
    private StatusCurso status;
    private int cargaHoraria;
    private Professor professor;
    private List<Double> notas;

    // Construtores
    public Curso(String nome, int cargaHoraria, Professor professor, StatusCurso StatusCurso) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.professor = professor;
        this.status = StatusCurso;
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
    public int getId() {
      return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public StatusCurso getStatus() {
        return status;
    }
    
    public void setStatus(StatusCurso status) {
        this.status = status;
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

    public void setNotas(Double nota) {
        this.notas.add(nota);
    }
}

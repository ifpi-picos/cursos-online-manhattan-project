package br.edu.ifpi.entidades;

public class Disciplina {
    private String nome;
    private int id;
    private int cargaHoraria;
    private String ementa;
    private Professor professor;

    // Construtores
    
    // Construtor sem id para facilitar a autenticação
    public Disciplina(String nome, int cargaHoraria, String ementa, Professor professor) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
        this.professor = professor;
    }

    // Construtor completo para manipulação no sistema
    public Disciplina(String nome, int id, int cargaHoraria, String ementa, Professor professor) {
        this.nome = nome;
        this.id = id;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
        this.professor = professor;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Não é necessário get e set para id, pois ele é gerado automaticamente pelo banco de dados.


    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
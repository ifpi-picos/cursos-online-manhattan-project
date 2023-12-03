package br.edu.ifpi.entities;

import br.edu.ifpi.enums.StatusAlunoCurso;

public class AlunoCurso {
    private Aluno aluno;
    private Curso curso;
    private Double nota1;
    private Double nota2;
    private Double nota3;
    private Double media;
    private StatusAlunoCurso statusAlunoCurso;

    // Construtores
    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
    }

    public AlunoCurso(Aluno aluno, Curso curso, Double nota1, Double nota2, Double nota3, Double media, StatusAlunoCurso statusAlunoCurso) {
        this.aluno = aluno;
        this.curso = curso;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.media = media;
        this.statusAlunoCurso = statusAlunoCurso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public Double getNota1() {
        return nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public StatusAlunoCurso getStatusAlunoCurso() {
        return statusAlunoCurso;
    }

    public void setStatusAlunoCurso(StatusAlunoCurso statusAlunoCurso) {
        this.statusAlunoCurso = statusAlunoCurso;
    }

    public void exibirNotas() {
        System.out.print("Notas do aluno " + aluno.getNome() + " no curso de " + curso.getNome() + ": ");
        System.out.println("Nota 1: " + getNota1());
        System.out.println("Nota 2: " + getNota2());
        System.out.println("Nota 3: " + getNota3());
        System.out.println(); // Pula uma linha após exibir as notas
    }

    public Double calcularMedia() {
        // Verifique se todas as notas foram atribuídas antes de calcular a média
        if (nota1 != null && nota2 != null && nota3 != null) {
            // Calcula a média das notas
            return (nota1 + nota2 + nota3) / 3.0;
        } else {
            // Se alguma nota não foi atribuída, retorna null ou lança uma exceção
            return null;
        }
    }

}
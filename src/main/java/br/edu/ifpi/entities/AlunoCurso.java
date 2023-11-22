package br.edu.ifpi.entities;

public class AlunoCurso {
    private Aluno aluno;
    private Curso curso;
    private Double nota1;
    private Double nota2;
    private Double nota3;

    //construtor
    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
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

    // public Double[] getNota() {
    //     return nota;
    // }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // public void setNota(Double[] nota) {
    //     this.nota = nota;
    // }

    // método para exibir o conteúdo do array de notas no terminal
    public void exibirNotas() {
        System.out.print("Notas do aluno " + aluno.getNome() + " no curso de " + curso.getNome() + ": ");
        System.out.println("Nota 1: " + getNota1());
        System.out.println("Nota 2: " + getNota2());
        System.out.println("Nota 3: " + getNota3());
        System.out.println(); // Pula uma linha após exibir as notas
    }

}
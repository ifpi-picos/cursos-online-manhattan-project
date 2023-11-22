package br.edu.ifpi.entities;

public class AlunoCurso {
    private Aluno aluno;
    private Curso curso;
    private Double[] nota;

    //construtor
    public AlunoCurso(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.nota = new Double[3];
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public Double[] getNota() {
        return nota;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setNota(Double[] nota) {
        this.nota = nota;
    }

    // método para exibir o conteúdo do array de notas no terminal
    public void exibirNotas() {
        System.out.print("Notas do aluno " + aluno.getNome() + " no curso de " + curso.getNome() + ": ");
        for (int i = 0; i < nota.length; i++) {
            System.out.print(nota[i] + " ");
        }
        System.out.println(); // Pula uma linha após exibir as notas
    }

}
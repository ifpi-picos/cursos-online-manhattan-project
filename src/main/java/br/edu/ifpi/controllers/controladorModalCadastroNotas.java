package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;

public class controladorModalCadastroNotas {

    @FXML
    private TextField inputNota1;

    @FXML
    private TextField inputNota2;

    @FXML
    private TextField inputNota3;

    @FXML
    private AnchorPane nodalBackground;

    @FXML
    private Button btnCadastrar;

    private Aluno alunoSelecionado;

    private int idCursoSelecionado;

    public void inicializar(Aluno aluno, int idCursoSelecionado) {
        this.alunoSelecionado = aluno;
        this.idCursoSelecionado = idCursoSelecionado;
    }

    @FXML
    private void handleCadastrar() throws SQLException {
        String textoNota1 = inputNota1.getText();
        String textoNota2 = inputNota2.getText();
        String textoNota3 = inputNota3.getText();

        // Converte os valores de texto para Double
        Double nota1 = Double.parseDouble(textoNota1);
        Double nota2 = Double.parseDouble(textoNota2);
        Double nota3 = Double.parseDouble(textoNota3);

        // Cria um array com as notas
        Double[] notas = {nota1, nota2, nota3};

        // Recebe o id do curso e do aluno
        int idCurso = idCursoSelecionado;
        int idAluno = alunoSelecionado.getId();

        // Cria uma conexão com o banco de dados
        Connection conexao = Conexao.getConnection();

        try {
            // Cria uma instância do AlunoCursoDao
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(conexao);

            // Cria uma instância de AlunoCurso com os dados
            AlunoCurso alunoCurso = new AlunoCurso(new Aluno(idAluno, null, null, null), null);
            alunoCurso.getAluno().setId(idAluno);
            alunoCurso.getCurso().setId(idCurso);
            alunoCurso.setNota(notas);

            // Chama o método cadastrarNotas
            int resultado = alunoCursoDao.cadastrarNotas(alunoCurso);

            if (resultado > 0) {
                System.out.println("Notas cadastradas com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar as notas.");
            }
        } finally {
            // Fecha a conexão
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
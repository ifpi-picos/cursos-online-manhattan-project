package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controladorModalCadastroNotas implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField inputNota1;

    @FXML
    private TextField inputNota2;

    @FXML
    private TextField inputNota3;

    @FXML
    private AnchorPane nodalBackground;

    private Aluno alunoSelecionado;

    private int idCursoSelecionado;

    public void inicializar(Aluno aluno, int idCursoSelecionado) {
        this.alunoSelecionado = aluno;
        this.idCursoSelecionado = idCursoSelecionado;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCadastrar.setOnAction(event -> cadastrarNotas());
    }


    public void cadastrarNotas()  {
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

        Connection conexao = null;
        try {
            // Cria uma conexão com o banco de dados
            conexao = Conexao.getConnection();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
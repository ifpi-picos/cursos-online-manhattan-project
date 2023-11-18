package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.enums.StatusAluno;

public class ControladorCadastroAluno implements Initializable {

    @FXML
    private Button Alunos;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button Home;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private ToggleGroup Status;

    @FXML
    private RadioButton ativo;

    @FXML
    private AnchorPane barraLateral;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField campoEmail;

    @FXML
    private RadioButton inativo;

    @FXML
    private TextField nomeAluno;

    @FXML
    private Button voltar;

    AlunoDao BDAluno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));
        voltar.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", voltar));
        //Inicia o radioButton como statusAluno.ATIVO
        ativo.fire();
        //
        cadastrar.setOnAction(e -> {
            gerarAluno();
        });

    }

    public void gerarAluno() {

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }
        BDAluno = new AlunoDao(conexao);

        StatusAluno status;
        String nome = nomeAluno.getText();
        String email = campoEmail.getText();
        
        if (sistema.validarEmail(email)) {
            System.out.println("E-mail válido");
        } else {
            exibirPopupErro(); ///emite mensagem de erro caso email seja invalido
        }

        // Verifica diretamente qual RadioButton está selecionado
        if (ativo.isSelected()) {
            status = StatusAluno.ATIVO;
        } else {
            status = StatusAluno.INATIVO;
        }
        //verifica se os campos foram preenchidos
        if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty()) {
            nomeAluno.clear();
            campoEmail.clear();
            Aluno aluno = new Aluno(nome, email, status);
            BDAluno.cadastrar(aluno);
        } else {
            nomeAluno.clear();
            campoEmail.clear();
            exibirPopupErro(); ///emite mensagem de erro caso exista um campo vazio
        }
    }


    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Dados não preenchidos ou inválidos");
        alert.showAndWait();
    }
}

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
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
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

    private String selectedValue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));

        Status.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // O código a ser executado quando uma opção é selecionada
                RadioButton selectedRadioButton = (RadioButton) newValue;
                selectedValue = selectedRadioButton.getText();
            }
        });


    }

    public Aluno gerarAluno() {
        StatusAluno status;
        String nome = nomeAluno.getText();
        String email = campoEmail.getText();
    
        if ("ATIVO".equals(selectedValue)) {
            status = StatusAluno.ATIVO;
        } else {
            status = StatusAluno.INATIVO;
        }
    
        if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty()) {
            Aluno aluno = new Aluno(nome, email, status);
            return aluno;
        } else {
            exibirPopupErro();
            return null; // Adicionado para indicar que a criação do aluno falhou
        }
    }


    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Campos precisam ser preenchidos");
        alert.showAndWait();
    }
}

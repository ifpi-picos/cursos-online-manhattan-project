package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusAluno;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class controladorCadastro implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ToggleGroup escolha;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    @FXML
    private RadioButton radioAluno;

    @FXML
    private RadioButton radioProfessor;

    AlunoDao BDAluno;
    ProfessorDao BDProfessor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnCadastrar));

        btnCadastrar.setOnAction(event -> gerarCadastro());
    }


    public void gerarCadastro(){
        Connection connection = null;
        try {
            connection = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        BDAluno = new AlunoDao(connection);
        BDProfessor = new ProfessorDao(connection);

        String nome = inputNome.getText();
        String email = inputEmail.getText();

        if (sistema.validarEmail(email)){
            System.out.println("Email válido");
        }else{
            exibirPopupErro();
            return;
        }

        if(email == null || email.isEmpty() || nome == null || nome.isEmpty()){
            exibirPopupErro();
            return;
        }

        if (radioAluno.isSelected()) {
            limparCampos();
            Aluno aluno = new Aluno(nome, email, StatusAluno.ATIVO);
            BDAluno.cadastrar(aluno);
        }else if(radioProfessor.isSelected()){
            limparCampos();
            Professor professor = new Professor(nome, email);
            BDProfessor.cadastrar(professor);
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Seleção de cadastro inválida!");
            alert.showAndWait();
        }

    }

    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Dados não preenchidos ou inválidos");
        alert.showAndWait();
    }

    public void limparCampos() {
        inputNome.clear();
        inputEmail.clear();
        radioAluno.setSelected(false);
        radioProfessor.setSelected(false);
    }
}

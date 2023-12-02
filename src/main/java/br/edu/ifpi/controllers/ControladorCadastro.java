package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControladorCadastro implements Initializable, SessaoController{

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

    private SessaoDao sessaoDao;

    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVoltar.setOnAction( event -> Sistema.trocarCena("/fxml/login.fxml", btnCadastrar,SessaoDao));
    }
}
package br.edu.ifpi.controllers;

import br.edu.ifpi.SessaoDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControladorCadastro {

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

    public ControladorCadastro (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }
}

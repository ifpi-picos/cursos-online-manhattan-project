package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controladorLogin implements Initializable {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private AnchorPane formsLogIn;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        btnCadastrar.setOnAction(event -> sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar));

    }

    public void autenticar(){
        String nome = inputNome.getText();
        String email = inputEmail.getText();


    }

}

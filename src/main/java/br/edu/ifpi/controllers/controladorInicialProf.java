package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import br.edu.ifpi.sistema;

public class controladorInicialProf implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

}

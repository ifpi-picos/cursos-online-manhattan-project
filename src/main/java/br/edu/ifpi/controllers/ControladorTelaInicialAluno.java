package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class ControladorTelaInicialAluno implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

}


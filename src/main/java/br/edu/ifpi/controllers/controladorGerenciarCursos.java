package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import br.edu.ifpi.sistema;


public class controladorGerenciarCursos implements Initializable {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<?, ?> colunaCargaHoraria;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableColumn<?, ?> colunaProfessor;

    @FXML
    private TableView<?> tabelaCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));

        btnAdicionar.setOnAction(event -> sistema.trocarCena("/fxml/cadastroCurso.fxml", btnAdicionar));
    }

}

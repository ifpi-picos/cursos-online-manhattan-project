package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class controladorGerenciarCursos {

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

}

package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import br.edu.ifpi.Sistema;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.entities.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorGerenciarCursos implements Initializable{
    @FXML
    private Button btnAdicionar;

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

    @FXML
    private TableColumn<?, ?> colunaCargaHoraria;

    @FXML
    private TableColumn<?, ?> colunaCargaHoraria1;

    @FXML
    private TableColumn<?, ?> colunaCargaHoraria2;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableColumn<AlunoCurso, String> colunaProfessor;

    @FXML
    private TableView<AlunoCurso> tabelaCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }
    
}

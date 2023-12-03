package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import br.edu.ifpi.Sistema;
import br.edu.ifpi.models.Cursoinfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Cursoinfo, String> colunaNome;

    @FXML
    private TableColumn<Cursoinfo, String> colunaProfessor;

    @FXML
    private TableColumn<Cursoinfo, Integer> colunaQuantAlunos;

    @FXML
    private TableColumn<Cursoinfo, Double> colunaAproveitamento;

    @FXML
    private TableView<Cursoinfo> tabelaCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colunaProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colunaQuantAlunos.setCellValueFactory(new PropertyValueFactory<>("quantAlunos"));
        colunaAproveitamento.setCellValueFactory(new PropertyValueFactory<>("aproveitamento"));
        
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }
    
}

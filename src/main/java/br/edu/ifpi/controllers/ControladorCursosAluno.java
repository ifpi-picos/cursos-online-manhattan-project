package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.entities.Curso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControladorCursosAluno implements Initializable, SessaoController{
    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInscrever;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Curso,> ColMatricula;

    @FXML
    private TableColumn<Curso, Integer> colunaCargaHoraria;

    @FXML
    private TableColumn<Curso, String> colunaNome;

    @FXML
    private TableColumn<Curso, String> colunaProfessor;

    @FXML
    private TableView<Curso> tabelaCursos;

    private SessaoDao sessaoDao;
    
    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos, sessaoDao));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos, sessaoDao));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil, sessaoDao));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos, sessaoDao));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair, sessaoDao));
    }
}

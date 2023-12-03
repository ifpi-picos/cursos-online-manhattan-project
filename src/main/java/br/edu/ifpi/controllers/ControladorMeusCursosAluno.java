package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

public class ControladorMeusCursosAluno implements Initializable,SessaoController {

    @FXML
    private TableColumn<?, ?> ColMedia;

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
    private TableColumn<?, ?> colMinhasNotas;

    @FXML
    private TableColumn<?, ?> colNome;

    @FXML
    private TableColumn<?, ?> colNota1;

    @FXML
    private TableColumn<?, ?> colNota2;

    @FXML
    private TableColumn<?, ?> colNota3;

    @FXML
    private TableColumn<?, ?> colProfessor;

    @FXML
    private MenuItem itemConcluidos;

    @FXML
    private MenuItem itemCursando;

    @FXML
    private MenuItem itemNaoConcluidos;

    @FXML
    private TableColumn<?, ?> mediaGeralCurso;

    @FXML
    private TableColumn<?, ?> statusMatricula;

    private SessaoDao sessaoDao;
    
    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mediaGeralCurso();

        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos, sessaoDao));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos, sessaoDao));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil, sessaoDao));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos, sessaoDao));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair, sessaoDao));
    }

    public void mediaGeralCurso() {
        double mediaGeralCurso = sessaoDao.alunoCursoDao.calcularMediaGeralDoCurso();


    }

}

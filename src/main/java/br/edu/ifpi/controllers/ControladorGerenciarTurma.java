package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.entities.Curso;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControladorGerenciarTurma implements Initializable {
    @FXML
    private Text NomeCurso;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnNota1;

    @FXML
    private Button btnNota2;

    @FXML
    private Button btnNota3;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Curso, String> colAluno;

    @FXML
    private TableColumn<Curso, Double> colNota1;

    @FXML
    private TableColumn<Curso, Double> colNota2;

    @FXML
    private TableColumn<Curso, Double> colNota3;

    @FXML
    private TextField inputNota1;

    @FXML
    private TextField inputNota2;

    @FXML
    private TextField inputNota3;

    @FXML
    private TableView<Curso> tabelaTurma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaDetalhesCurso {
    @FXML
    private Button adicionarAlunos;

    @FXML
    private Button alunosBotao;

    @FXML
    private Button configuracoesBotao;

    @FXML
    private Button cursosBotao;

    @FXML
    private Button detalhesAlunos;

    @FXML
    private TextField detalhesCargaHoraria;

    @FXML
    private TextField detalhesNomeCurso;

    @FXML
    private TextField detalhesProfNome;

    @FXML
    private ChoiceBox<?> detalhesstatusCurso;

    @FXML
    private AnchorPane formulario1;

    @FXML
    private Button homeBotao;

    @FXML
    private ListView<?> listaAlunos;

    @FXML
    private Button permissoesContasbotao;

    @FXML
    private Button professoresBotao;

    @FXML
    private Button removerAlunos;
}

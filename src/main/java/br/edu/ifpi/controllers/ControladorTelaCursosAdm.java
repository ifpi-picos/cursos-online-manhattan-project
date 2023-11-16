package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.System;
import br.edu.ifpi.entidades.Curso;

public class ControladorTelaCursosAdm implements Initializable{

    @FXML
    private AnchorPane AdmCursosInicial;

    @FXML
    private Button Alunos;

    @FXML
    private AnchorPane Background;

    @FXML
    private Button CadastrarCursoBotao;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button Home;

    @FXML
    private ListView<Curso> ListaCursos;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private AnchorPane barraLateral;

    @FXML
    private Button detalhesCurso;

    @FXML
    private Button editarCursoBotao;

    @FXML
    private Button excluirCursoBotao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Home.setOnAction(event -> System.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> System.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> System.trocarCena("/fxml/telaLogIn.fxml",Sair));
    }

}

package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.System;

public class controladorTelaGerenciamentoProf implements Initializable{

    @FXML
    private Button Alunos;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button Home;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private AnchorPane barraLateral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> System.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> System.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> System.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> System.trocarCena("/fxml/telaLogInS.fxml",Sair));
    }

}

package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.sistema;
import br.edu.ifpi.entidades.Aluno;
public class ControladorTelaGerenciamentoAlunos implements Initializable{

    @FXML
    private Button Alunos;

    @FXML
    private Button CadastrarAluno;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button EditarAlunos;

    @FXML
    private Button Home;

    @FXML
    private ListView<Aluno> ListaAlunos;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private AnchorPane barraLateral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));

        ObservableList<Aluno> alunos = FXCollections.observableArrayList();
        ListaAlunos.setItems(alunos);
    }



}

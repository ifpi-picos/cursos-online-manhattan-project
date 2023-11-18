package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
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
    private ListView<Curso> ListaCursos = new ListView<>();

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

    CursoDao BDCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));
        
        // Criar uma instância de Connection usando a classe Conexao
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chama o construtor de CursoDao passando a conexão como argumento
        BDCurso = new CursoDao(conexao);
        // Obter a lista de cursos do banco de dados
        List<Curso> cursosDoBanco = BDCurso.consultarTodos();
        
        // Criar uma ObservableList a partir da lista de cursos
        ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursosDoBanco);
        
        // Configurar o ListView para exibir a lista de cursos
        ListaCursos.setItems(observableCursos);
        
        // Verificar se a lista de cursos está vazia e exibir uma mensagem se necessário
        if (observableCursos.isEmpty()) {
            ListaCursos.setPlaceholder(new Label("Nenhum curso disponível."));
        }
        

        

        CadastrarCursoBotao.setOnAction(event -> sistema.trocarCena("/fxml/telaCadastroCurso.fxml", CadastrarCursoBotao));
        
        detalhesCurso.setOnAction(event -> sistema.trocarCena("/fxml/telaDetalhesCurso.fxml", detalhesCurso));

    }

}

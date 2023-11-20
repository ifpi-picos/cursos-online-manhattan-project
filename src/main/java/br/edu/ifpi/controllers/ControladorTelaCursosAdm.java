package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entidades.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaCursosAdm implements Initializable {

    @FXML
    private AnchorPane AdmCursosInicial;

    @FXML
    private Button Alunos;

    @FXML
    private Button CadastrarCursoBotao;

    @FXML
    private TableColumn<Curso, String> ColunaNome;

    @FXML
    private TableColumn<Curso, Integer> ColunaID;

    @FXML
    private TableColumn<Curso, String> ColunaStatus;

    @FXML
    private TableColumn<Curso, String> ColunaCargaHoraria;

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

    @FXML
    private Button detalhesCurso;

    @FXML
    private Button editarCursoBotao;

    @FXML
    private Button excluirCursoBotao;

    @FXML
    private TableView<Curso> tabelaCursos;

    CursoDao BDCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml", Sair));
        //Fazendo conexão com o banco de dados para carregar os arquivos na página
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();

            // Chama o construtor de CursoDao passando a conexão como argumento
            BDCurso = new CursoDao(conexao);
            // Obter a lista de cursos do banco de dados
            List<Curso> cursosDoBanco = BDCurso.consultarTodos();
            // Criar uma ObservableList a partir da lista de cursos
            ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursosDoBanco);

            // Configurar o TableView e as colunas
            ColunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ColunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            ColunaCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));

            // Configurar a TableView para exibir a lista de cursos
            tabelaCursos.setItems(observableCursos);

            // Configurar seleção única
            tabelaCursos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Certifique-se de fechar a conexão no bloco finally
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        CadastrarCursoBotao.setOnAction(event -> sistema.trocarCena("/fxml/telaCadastroCurso.fxml", CadastrarCursoBotao));
        // detalhesCurso.setOnAction(event -> );
    }

    // // Método para lidar com o botão "Editar"
    // @FXML
    // private void handleEditarCurso() {
    //     Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();
    //     if (cursoSelecionado != null) {
    //         // Chamar a função para editar o curso
    //         System.out.println("Editar curso: " + cursoSelecionado);
    //     }
    // }

    // // Método para lidar com o botão "Excluir"
    // @FXML
    // private void handleExcluirCurso() {
    //     Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();
    //     if (cursoSelecionado != null) {
    //         // Chamar a função para excluir o curso
    //         System.out.println("Excluir curso: " + cursoSelecionado);
    //     }
    // }

    // // Método para lidar com o botão "Detalhes"
    // @FXML
    // private void handleDetalhesCurso() {
    //     Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();
    //     if (cursoSelecionado != null) {
    //         // Chamar a função para exibir detalhes do curso
    //         System.out.println("Detalhes do curso: " + cursoSelecionado);
    //     }
    // }
}


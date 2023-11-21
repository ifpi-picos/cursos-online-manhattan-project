package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entities.Curso;


public class controladorGerenciarCursos implements Initializable {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Curso, Integer> colunaCargaHoraria;

    @FXML
    private TableColumn<Curso, String> colunaNome;

    @FXML
    private TableColumn<Curso, String> colunaProfessor;

    @FXML
    private TableView<Curso> tabelaCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));

        btnAdicionar.setOnAction(event -> sistema.trocarCena("/fxml/cadastroCurso.fxml", btnAdicionar));
        btnRemover.setOnAction(event -> removerCurso());

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();

            // Chama o construtor de CursoDao passando a conexão como argumento
            CursoDao BDCurso = new CursoDao(conexao);
            // Obter a lista de cursos do banco de dados
            List<Curso> cursosDoBanco = BDCurso.consultarTodos();
            // Criar uma ObservableList a partir da lista de cursos
            ObservableList<Curso> observableCursos = FXCollections.observableArrayList(cursosDoBanco);

            // Configurar o TableView e as colunas
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
            colunaProfessor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfessor().getNome()));
            

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
    }

    // Função para remover um curso
    public void removerCurso() {
        sistema sistema = new sistema();

        // Obter o curso selecionado
        Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();

        // Se nenhum curso foi selecionado, exibir mensagem de erro
        if (cursoSelecionado == null) {
            sistema.exibirPopupErro("Nenhum curso selecionado!");
            return;
        }

        // Obter a conexão com o banco de dados
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();

            // Chamar o método de remoção do curso
            CursoDao BDCurso = new CursoDao(conexao);
            BDCurso.remover(cursoSelecionado);

            // Remover o curso da tabela
            tabelaCursos.getItems().remove(cursoSelecionado);

            // Exibir mensagem de sucesso
            sistema.exibirPopupSucesso("Curso removido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            sistema.exibirPopupErro("Erro ao remover curso!");
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
    }
}

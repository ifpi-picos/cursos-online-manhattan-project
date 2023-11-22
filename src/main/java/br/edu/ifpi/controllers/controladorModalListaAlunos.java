package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class controladorModalListaAlunos {

    @FXML
    private Button btnCadastrarNotas;

    @FXML
    private TableColumn<Aluno, String> colunaAlunos;

    @FXML
    private TableView<Aluno> tabelaAlunos;

    private ObservableList<Aluno> listaAlunos;

    private Aluno alunoSelecionado;

    private int idCursoSelecionado;

    public void inicializar(int idCursoSelecionado) {
        this.idCursoSelecionado = idCursoSelecionado;
        carregarListaAlunos();
    }

    @FXML
    private void initialize() {
        colunaAlunos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        btnCadastrarNotas.setOnAction(event -> abrirModalCadastroNotas(alunoSelecionado));
        
    }

    private void carregarListaAlunos() {
        try {
            // Obtém a lista de alunos associados ao curso
            listaAlunos = FXCollections.observableArrayList(new AlunoCursoDao(Conexao.getConnection()).consultarAlunosPorCurso(idCursoSelecionado));
            // Configura a lista de alunos na tabela
            tabelaAlunos.setItems(listaAlunos);
        } catch (Exception e) {
            e.printStackTrace();
            // Lida com exceções ao carregar a lista de alunos
        }
    }

    private void abrirModalCadastroNotas(Aluno aluno) {
        try {
            // Carrega o arquivo FXML do modal de cadastro de notas
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modalCadastroNotas.fxml"));
            Parent root = loader.load();

            // Obtém o controlador do modal
            controladorModalCadastroNotas modalCadastroNotasController = loader.getController();
            modalCadastroNotasController.inicializar(aluno);

            // Cria um novo palco para o modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Cadastro de Notas");
            modalStage.setScene(new Scene(root));

            // Exibe o modal e aguarda até que seja fechado
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace(); // Lida com exceções ao carregar o FXML
        }
    }
}
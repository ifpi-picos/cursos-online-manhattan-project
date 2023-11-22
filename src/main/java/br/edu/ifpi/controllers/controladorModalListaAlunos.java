package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button btnVoltar;

    @FXML
    private TableColumn<Aluno, String> colunaAlunos;

    @FXML
    private TableView<Aluno> tabelaAlunos;

    private ObservableList<Aluno> listaAlunos;

    private int idCursoSelecionado;

    public void inicializar(int idCursoSelecionado) {
        this.idCursoSelecionado = idCursoSelecionado;
        carregarListaAlunos();
    }

    @FXML
    private void initialize() {
        colunaAlunos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        
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
}
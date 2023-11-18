package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Professor;
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

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEditar;

    @FXML
    private TableColumn<?, ?> colunaEmail;

    @FXML
    private TableColumn<?, ?> colunaID;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableView<Professor> tabelaProfessores;

    ProfessorDao BDprof;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));

        btnCadastrar.setOnAction(event -> sistema.trocarCena("/fxml/TelaCadastroProf.fxml",btnCadastrar));

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            BDprof = new ProfessorDao(conexao);
            //Obter a lista de alunos do banco de dados.
            List<Professor> ProfessorBD = BDprof.consultarTodos();
            // Criar uma ObservableList a partir da lista de cursos
            ObservableList<Professor> observableProfessores = FXCollections.observableArrayList(ProfessorBD);
            // Preencher a tabela com os alunos
            tabelaProfessores.setItems(observableProfessores);
            // Configurar o TableView e as colunas
            colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            // Configurar seleção única
            tabelaProfessores.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        }catch (SQLException e) {
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

}

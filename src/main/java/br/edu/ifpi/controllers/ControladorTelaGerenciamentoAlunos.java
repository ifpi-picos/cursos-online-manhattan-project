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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entidades.Aluno;
public class ControladorTelaGerenciamentoAlunos implements Initializable{

    @FXML
    private Button Alunos;

    @FXML
    private Button CadastrarAluno;

    @FXML
    private TableColumn<?, ?> ColunaEmail;

    @FXML
    private TableColumn<?, ?> ColunaID;

    @FXML
    private TableColumn<?, ?> ColunaNome;

    @FXML
    private TableColumn<?, ?> ColunaStatus;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button EditarAlunos;

    @FXML
    private Button Home;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private TableView<Aluno> TabelaAluno;

    @FXML
    private AnchorPane barraLateral;

    AlunoDao BDAluno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));
        
        CadastrarAluno.setOnAction(event -> sistema.trocarCena("/fxml/telaCadastroAluno.fxml", CadastrarAluno));

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            BDAluno = new AlunoDao(conexao);
            //Obter a lista de alunos do banco de dados.
            List<Aluno> AlunosBD = BDAluno.consultarTodos();
            // Criar uma ObservableList a partir da lista de cursos
            ObservableList<Aluno> observableAlunos = FXCollections.observableArrayList(AlunosBD);
            // Preencher a tabela com os alunos
            TabelaAluno.setItems(observableAlunos);
            // Configurar o TableView e as colunas
            ColunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
            ColunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            ColunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            ColunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            // Configurar seleção única
            TabelaAluno.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


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

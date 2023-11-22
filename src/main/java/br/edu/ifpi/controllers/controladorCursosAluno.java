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
import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.entities.Curso;

public class controladorCursosAluno implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInscrever;

    @FXML
    private Button btnPerfil;

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


        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/cursosAluno.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilAluno.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

    public void cadastrarAlunoCurso(){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(conexao);
            AlunoDao alunoDao = new AlunoDao(conexao);
            Aluno aluno = alunoDao.consultarPorNomeEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();
            AlunoCurso alunoCurso = new AlunoCurso(aluno, cursoSelecionado)
            alunoCursoDao.cadastrar(alunoCurso);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

package br.edu.ifpi.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.dao.Conexao;

public class controladorPerfilProfessor implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private Text carregarEmail;

    @FXML
    private Text carregarNome;

    @FXML
    private TableColumn<Curso, String> colunaNomeCursos;

    @FXML
    private TableView<Curso> cursosCadastrado;

    @FXML
    private Button inserirNota;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Configura o valor que irá ser exibido na coluna 
        colunaNomeCursos.setCellValueFactory(new PropertyValueFactory<>("nome"));

        //Configura a seleção de apenas uma linha da coluna
        cursosCadastrado.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Carrega os dados do usuário que está logado na sessão
        carregarNome.setText(SessaoUsuario.getNomeUsuario());
        carregarEmail.setText(SessaoUsuario.getEmailUsuario());

        //Obtém a lista de cursos o qual o professor que está logado ministra
        try (Connection connection = Conexao.getConnection()) {
            CursoDao cursoDao = new CursoDao(connection);
            ProfessorDao professorDao = new ProfessorDao(connection);
            //Carrega o professor através dos dados da sessão
            Professor professor = professorDao.buscarPorNomeEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            List<Curso> listaCursos = cursoDao.buscarPorId(professor.getId());
            // Configurar a lista de cursos na tabela
            cursosCadastrado.getItems().setAll(listaCursos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //configura as ações dos botões do menu lateral
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml", btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialprof.fxml", btnVoltar));
        inserirNota.setOnAction(event -> abrirModalListaAlunos());
    }


    private void abrirModalListaAlunos() {
        try {
            // Carrega o arquivo FXML do modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modalListadeAlunos.fxml"));
            Parent root = loader.load();

            // Obtém o controlador do modal
            controladorModalListaAlunos modalController = loader.getController();

            // Define o ID do curso selecionado no controlador do modal
            int idCursoSelecionado = obterIdCursoSelecionado(); // Substitua por sua lógica de obtenção do ID do curso
            modalController.inicializar(idCursoSelecionado);

            // Cria um novo palco para o modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Lista de Alunos");
            modalStage.setScene(new Scene(root));

            // Exibe o modal e aguarda até que seja fechado
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace(); // Lida com exceções ao carregar o FXML
        }
    }

    private int obterIdCursoSelecionado() {
        Curso cursoSelecionado = cursosCadastrado.getSelectionModel().getSelectedItem();
        return cursoSelecionado.getId();
    }
    
}

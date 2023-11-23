package br.edu.ifpi.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Curso;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class controladorPerfilAluno implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVerNotas;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Curso, String> colunaNomeCursos;

    @FXML
    private TableView<Curso> cursosCadastrado;

    @FXML
    private Text textEmail;

    @FXML
    private Text textNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNomeCursos.setCellValueFactory(new PropertyValueFactory<>("nome"));

        textNome.setText(SessaoUsuario.getNomeUsuario());
        textEmail.setText(SessaoUsuario.getEmailUsuario());

        try (Connection connection = Conexao.getConnection()){
            AlunoDao alunoDao = new AlunoDao(connection);
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(connection);

            Aluno aluno = alunoDao.consultarPorNomeEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            int alunoId = aluno.getId();
            
            // Obter a lista de cursos vinculados ao aluno
            List<Curso> cursos = alunoCursoDao.consultarCursosPorAluno(alunoId);
            
            // Exibir a lista de cursos na tabela
            cursosCadastrado.getItems().setAll(cursos);
        } catch (Exception e) {
            sistema sistema = new sistema();
            sistema.exibirPopupErro("Erro ao carregar cursos...");
        }
        
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/cursosAluno.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilAluno.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnVoltar));
        btnVerNotas.setOnAction(event -> abrirModalNotas());
    }

    private void abrirModalNotas(){
        try {
            // Carrega o arquivo FXML do modal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modalAluno.fxml"));
            Parent root = loader.load();
            // Obtém o controlador do modal
            controladorModalAluno modalController = loader.getController();
            //Obtém o aluno e o curso para passar para carregamento das notas no modal de notas
            Curso cursoSelecionado = cursosCadastrado.getSelectionModel().getSelectedItem();
            modalController.inicializar(cursoSelecionado);

            // Cria um novo palco para o modal
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Notas");
            modalStage.setScene(new Scene(root));
            // Exibe o modal e aguarda até que seja fechado
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }          
    }

}

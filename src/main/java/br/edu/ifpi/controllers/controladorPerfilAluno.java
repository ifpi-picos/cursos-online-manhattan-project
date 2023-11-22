package br.edu.ifpi.controllers;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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
            // TODO: handle exception
        }
        
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/cursosAluno.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilAluno.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnVoltar));
    }

}

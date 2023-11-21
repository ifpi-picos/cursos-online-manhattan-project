package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
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
        colunaNomeCursos.setCellValueFactory(new PropertyValueFactory<>("nome"));

        carregarNome.setText(SessaoUsuario.getNomeUsuario());
        carregarEmail.setText(SessaoUsuario.getEmailUsuario());

        try (Connection connection = Conexao.getConnection()) {
            CursoDao cursoDao = new CursoDao(connection);
            ProfessorDao professorDao = new ProfessorDao(connection);

            Professor professor = professorDao.buscarPorNomeEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            List<Curso> listaCursos = cursoDao.buscarPorId(professor.getId());

            // Configurar a lista de cursos na tabela
            cursosCadastrado.getItems().setAll(listaCursos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml", btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialprof.fxml", btnVoltar));
    }
}

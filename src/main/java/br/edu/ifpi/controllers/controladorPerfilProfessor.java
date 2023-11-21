package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.dao.Conexao;

public class controladorPerfilProfessor implements Initializable {

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnDetalhes;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField carregarEmail;

    @FXML
    private TextField carregarNome;

    @FXML
    private TableColumn<?, ?> colunaNomeCursos;

    @FXML
    private TableView<?> cursosCadastrado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));

        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialprof.fxml", btnCursos));

        
    }

    // Carregar dados do professor que está logado
    public List<Professor> carregarDadosProfessor(String nome, String email) {
    List<Professor> professores = new ArrayList<>();

    try (Connection conexao = Conexao.getConnection()) {
        // Carregar cursos que o professor ministra
        CursoDao cursoDao = new CursoDao(conexao);
        List<Curso> cursos = cursoDao.buscarPorNomeEmail(nome, email);

        Professor professor = new Professor(email, email);
        professor.setNome(nome);
        professor.setEmail(email);
        professor.setCursos(cursos);

        professores.add(professor);
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return professores;
}

}

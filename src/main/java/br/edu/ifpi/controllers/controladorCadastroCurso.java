package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Professor;

public class controladorCadastroCurso implements Initializable {

    @FXML
    private Button btnCadastrar;

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
    private TextField inputHoras;

    @FXML
    private TextField inputNome;

    @FXML
    private ChoiceBox<Professor> selectProfessor;

    @FXML
    private ToggleGroup status;

    @FXML
    private RadioButton statusAberto;

    @FXML
    private RadioButton statusFechado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCursos.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml",btnCursos));
        btnHome.setOnAction(event -> sistema.trocarCena("/fxml/telaInicialProf.fxml", btnHome));
        btnPerfil.setOnAction(event -> sistema.trocarCena("/fxml/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> sistema.trocarCena("/fxml/login.fxml", btnSair));

        btnCadastrar.setOnAction(null);
    }


    public void cadastrarCurso(){
        String nome = inputNome.getText();
    }

    // Função para carregar uma lista de professores
    public void carregarProfessores(){

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        ProfessorDao professorDao = new ProfessorDao(conexao);
        List<Professor> professores = professorDao.consultarTodos();

        
    }
}

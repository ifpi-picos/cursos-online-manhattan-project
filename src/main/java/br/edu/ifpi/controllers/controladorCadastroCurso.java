package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import br.edu.ifpi.enums.StatusCurso;

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
        carregarProfessores();
        statusAberto.fire();
        btnCadastrar.setOnAction(event -> cadastrarCurso());
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/gerenciarCursos.fxml", btnVoltar));
    }


    public void cadastrarCurso(){
        String nome = inputNome.getText();
        String cargaHorariaTexto = inputHoras.getText();
        StatusCurso status;
        Professor professor;

        if (statusAberto.isSelected()) {
            status = StatusCurso.ABERTO;
        } else {
            status = StatusCurso.FECHADO;
        }

        if (selectProfessor != null) {
            professor = selectProfessor.getValue();
        } else {
            exibirPopupErro();
            return;
        }

        Connection connection = null;
        try {
            connection = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CursoDao BDCurso = new CursoDao(connection);

        if (nome == null || nome.isEmpty() || cargaHorariaTexto == null || cargaHorariaTexto.isEmpty()) {
            exibirPopupErro();
            return;
        }

        int cargaHoraria;
        try {
            cargaHoraria = Integer.parseInt(cargaHorariaTexto);
        } catch (NumberFormatException e) {
            exibirPopupErro();
            return;
        }

        if (statusAberto.isSelected()) {
            status = StatusCurso.ABERTO;
            limparCampos();
            Curso curso = new Curso(nome, cargaHoraria, professor, status);
            //System.out.println(curso.getNome() + " "+curso.getCargaHoraria() + " "+curso.getProfessor().getId() + " " + curso.getProfessor().getNome() + " "+ curso.getStatus());
            BDCurso.cadastrar(curso);
        } else{
            status = StatusCurso.FECHADO;
            limparCampos();
            Curso curso = new Curso(nome, cargaHoraria, professor, status);
            BDCurso.cadastrar(curso);
        }


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

        selectProfessor.getItems().addAll(professores);
        
    }

    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Dados não preenchidos ou inválidos");
        alert.showAndWait();
    }

    public void limparCampos() {
        inputNome.clear();
        inputHoras.clear();
        statusAberto.setSelected(false);
        statusFechado.setSelected(false);
    }
}

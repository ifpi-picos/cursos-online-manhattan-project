package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.models.Cursoinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorGerenciarCursos implements Initializable{
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Cursoinfo, String> colunaNome;

    @FXML
    private TableColumn<Cursoinfo, String> colunaProfessor;

    @FXML
    private TableColumn<Cursoinfo, Integer> colunaQuantAlunos;

    @FXML
    private TableColumn<Cursoinfo, Double> colunaAproveitamento;

    @FXML
    private TableColumn<Cursoinfo, Integer> colunaCargaHoraria;

    @FXML
    private TableView<Cursoinfo> tabelaCursos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        carregarTabela();

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colunaProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colunaQuantAlunos.setCellValueFactory(new PropertyValueFactory<>("quantAlunos"));
        colunaAproveitamento.setCellValueFactory(new PropertyValueFactory<>("aproveitamento"));
        colunaCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnAdicionar.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/cadastroCurso.fxml", btnAdicionar));
    }
    

    public void carregarTabela() {
        try {
            CursoDao cursoDao = new CursoDao(Conexao.getConnection());
            Cursoinfo cursoinfo = new Cursoinfo();

            List<Curso> cursos = cursoDao.consultarTodos();
            List<Cursoinfo> cursosinfo = cursoinfo.gerarListaCursoinfo(cursos);

            ObservableList<Cursoinfo> cursosObservable = FXCollections.observableArrayList(cursosinfo);
            tabelaCursos.setItems(cursosObservable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

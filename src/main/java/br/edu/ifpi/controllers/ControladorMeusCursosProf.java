package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.models.Cursoinfo;
import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Curso;
import br.edu.ifpi.entities.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorMeusCursosProf implements Initializable {
    @FXML
    private Button btnCursos;

    @FXML
    private Button btnFecharCurso;

    @FXML
    private Button btnGerenciarTurma;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Cursoinfo, String> colNome;

    @FXML
    private TableColumn<Cursoinfo, Double> colMediaGeral;

    @FXML
    private TableColumn<Cursoinfo, Integer> colQuantAlunos;

    @FXML
    private TableColumn<Cursoinfo, String> colStatusCurso;

    @FXML
    private TableView<Cursoinfo> tabelaCursosProfessor;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        carregarCursos();
        // Configure as colunas da tabela para exibir os dados corretos.
        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colMediaGeral.setCellValueFactory(new PropertyValueFactory<>("mediaGeralCurso"));
        colQuantAlunos.setCellValueFactory(new PropertyValueFactory<>("quantAlunosCursando"));
        colStatusCurso.setCellValueFactory(new PropertyValueFactory<>("statusCurso"));

        // Defina os manipuladores de eventos para os botões.
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

    public void carregarCursos(){
        try {
            CursoDao cursoDao = new CursoDao(Conexao.getConnection());
            ProfessorDao professorDao = new ProfessorDao(Conexao.getConnection());
            Cursoinfo cursoinfo = new Cursoinfo();

            Professor professor = professorDao.buscarPorNomeEEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            List<Curso> cursos = cursoDao.consultarCursosPorProfessor(professor.getId());
            List<Cursoinfo> cursosProf = cursoinfo.gerarListaCursoinfo(cursos);
            ObservableList<Cursoinfo> observableCursosProfessor = FXCollections.observableArrayList(cursosProf);
            tabelaCursosProfessor.setItems(observableCursosProfessor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
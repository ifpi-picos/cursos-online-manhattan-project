package br.edu.ifpi.controllers;

import java.io.IOException;
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
import br.edu.ifpi.enums.StatusCurso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

        // Move a configuração do evento btnGerenciarTurma para fora do ouvinte
        btnGerenciarTurma.setOnAction(event -> {
            Cursoinfo cursoSelecionado = tabelaCursosProfessor.getSelectionModel().getSelectedItem();
            if (cursoSelecionado != null) {
                Curso curso = cursoSelecionado.getCurso();
                trocarCena("/fxml/telasProfessor/gerenciarTurma.fxml", btnGerenciarTurma, curso);
            }else {
                Sistema.exibirPopupErro("Selecione um curso.");
            }
        });

        // tabelaCursosProfessor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        //     if (newValue != null) {
        //         Cursoinfo cursoSelecionado = tabelaCursosProfessor.getSelectionModel().getSelectedItem();
        //         Curso curso = cursoSelecionado.getCurso();
        //         btnGerenciarTurma.setOnAction(event -> trocarCena("/fxml/telasProfessor/gerenciarTurma.fxml", btnGerenciarTurma,curso));
        //     }
        // });

        colNome.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colMediaGeral.setCellValueFactory(new PropertyValueFactory<>("mediaGeralCurso"));
        colQuantAlunos.setCellValueFactory(new PropertyValueFactory<>("quantAlunosCursando"));
        colStatusCurso.setCellValueFactory(new PropertyValueFactory<>("statusCurso"));
        
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnFecharCurso.setOnAction(event -> fecharCurso());
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

    public static void trocarCena(String caminhoFXML, Button botao, Curso curso) {
        try {
            if (caminhoFXML == null || botao == null || curso == null) {
                Sistema.exibirPopupErro("Parametro inválido");
                return;
            }

            FXMLLoader loader = new FXMLLoader(Sistema.class.getResource(caminhoFXML));
            Parent novaCena = loader.load();
            ControladorGerenciarTurma controller = loader.getController();
            controller.inicializar(curso);
        
            Stage palco = (Stage) botao.getScene().getWindow();
            Scene novaScene = new Scene(novaCena);
            palco.setScene(novaScene);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void fecharCurso() {
        Cursoinfo cursoSelecionado = tabelaCursosProfessor.getSelectionModel().getSelectedItem();

        if (cursoSelecionado.getStatusCurso().equals(StatusCurso.FECHADO)) {
            Sistema.exibirPopupErro("O curso já está fechado!");
            return;
        }

        Curso curso = cursoSelecionado.getCurso();

        try {
            CursoDao cursoDao = new CursoDao(Conexao.getConnection());
            int resultado = cursoDao.fecharCurso(curso.getId());

            if (resultado == 1) {
                carregarCursos();
                Sistema.exibirPopupSucesso("Curso fechado com sucesso!");
            } else {
                Sistema.exibirPopupErro("Erro ao fechar curso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
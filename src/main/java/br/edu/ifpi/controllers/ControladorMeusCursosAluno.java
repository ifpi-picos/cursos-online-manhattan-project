package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.enums.StatusAlunoCurso;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorMeusCursosAluno implements Initializable,SessaoController {

    @FXML
    private TableColumn<AlunoCurso, Double> ColMedia;

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
    private TableColumn<?, ?> colMinhasNotas;

    @FXML
    private TableColumn<AlunoCurso, String> colNome;

    @FXML
    private TableColumn<AlunoCurso, Double> colNota1;

    @FXML
    private TableColumn<AlunoCurso, Double> colNota2;

    @FXML
    private TableColumn<AlunoCurso, Double> colNota3;

    @FXML
    private TableColumn<AlunoCurso, String> colProfessor;

    @FXML
    private MenuItem itemConcluidos;

    @FXML
    private MenuItem itemCursando;

    @FXML
    private MenuItem itemNaoConcluidos;

    @FXML
    private TableColumn<AlunoCurso, Double> mediaGeralCurso;

    @FXML
    private TableColumn<AlunoCurso, StatusAlunoCurso> statusMatricula;

    @FXML
    private TableView<AlunoCurso> tabelaCursos;

    private SessaoDao sessaoDao;
    
    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTabela();

        colNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso().getNome()));
        colProfessor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso().getProfessor().getNome()));
        colNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        colNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        colNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));
        ColMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
        colProfessor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso().getProfessor().getNome()));
        statusMatricula.setCellValueFactory(new PropertyValueFactory<>("statusAlunoCurso"));

        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos, sessaoDao));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos, sessaoDao));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil, sessaoDao));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos, sessaoDao));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair, sessaoDao));
    }

    // Função p/ carregar a tabela de cursos do aluno
    public void carregarTabela(){
        try {
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
            AlunoDao alunoDao = new AlunoDao(Conexao.getConnection());

            Aluno aluno = alunoDao.buscarPorNomeEEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            
            List<AlunoCurso> cursos = alunoCursoDao.consultarCursosMatriculados(aluno.getId());
            
            // Configuração da coluna para exibir a média geral
            mediaGeralCurso.setCellValueFactory(cellData -> {
                AlunoCurso alunoCurso = cellData.getValue();
                double mediaGeral = alunoCursoDao.calcularMediaGeralPorCurso(alunoCurso.getCurso().getId());
                return new SimpleDoubleProperty(mediaGeral).asObject();
            });
            
            ObservableList<AlunoCurso> cursosObservable = FXCollections.observableArrayList(cursos);

            tabelaCursos.setItems(cursosObservable);
            // List<AlunoCurso> cursos = sessaoDao.getAluno().getCursos();
            // tabelaCursos.getItems().setAll(cursos);
        } catch (Exception e) {
            e.printStackTrace();
            Sistema.exibirPopupErro("Erro ao se conectar ao Banco de dados");
        }
    }
}

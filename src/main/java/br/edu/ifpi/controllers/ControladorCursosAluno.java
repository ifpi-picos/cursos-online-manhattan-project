package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControladorCursosAluno implements Initializable, SessaoController{
    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInscrever;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<AlunoCurso, StatusAlunoCurso> ColMatricula;

    @FXML
    private TableColumn<AlunoCurso, Integer> colunaCargaHoraria;

    @FXML
    private TableColumn<AlunoCurso, String> colunaNome;

    @FXML
    private TableColumn<AlunoCurso, String> colunaProfessor;

    @FXML
    private TableView<AlunoCurso> tabelaCursos;

    private SessaoDao sessaoDao;
    
    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        carregarTabela();

        ColMatricula.setCellValueFactory(new PropertyValueFactory<>("statusAlunoCurso"));
        colunaCargaHoraria.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCurso().getCargaHoraria()).asObject());
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso().getNome()));
        colunaProfessor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCurso().getProfessor().getNome()));
    
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos, sessaoDao));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos, sessaoDao));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil, sessaoDao));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos, sessaoDao));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair, sessaoDao));
    }

    public void carregarTabela(){
        try {
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
            AlunoDao alunoDao = new AlunoDao(Conexao.getConnection());

            Aluno aluno = alunoDao.buscarPorNomeEEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
            
            List<AlunoCurso> cursos = alunoCursoDao.consultarCursosAbertosParaAluno(aluno.getId());
            ObservableList<AlunoCurso> cursosObservable = FXCollections.observableArrayList(cursos);

            tabelaCursos.setItems(cursosObservable);
        } catch (SQLException e) {
            e.printStackTrace();
            Sistema.exibirPopupErro("Erro ao se conectar ao Banco de dados");
        }
    }

}

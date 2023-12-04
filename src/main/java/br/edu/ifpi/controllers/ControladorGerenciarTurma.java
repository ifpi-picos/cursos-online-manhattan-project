package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.entities.Curso;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ControladorGerenciarTurma implements Initializable {
    @FXML
    private Text NomeCurso;

    @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnNota1;

    @FXML
    private Button btnNota2;

    @FXML
    private Button btnNota3;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<AlunoCurso, String> colAluno; 

    @FXML
    private TableColumn<AlunoCurso, Double> colNota1; 

    @FXML
    private TableColumn<AlunoCurso, Double> colNota2; 

    @FXML
    private TableColumn<AlunoCurso, Double> colNota3;

    @FXML
    private TableView<AlunoCurso> tabelaTurma; 


    @FXML
    private TextField inputNota1;

    @FXML
    private TextField inputNota2;

    @FXML
    private TextField inputNota3;

    Curso curso;

    public void inicializar (Curso curso){
        this.curso = curso;
        carregarDadosTabela();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        colAluno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAluno().getNome()));
        colNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        colNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        colNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));
        
        tabelaTurma.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                AlunoCurso alunoCursoSelecionado = tabelaTurma.getSelectionModel().getSelectedItem();
            }
        });

        btnNota1.setOnAction(event -> tornarVisivelInputNota1());
        btnCadastrar.setOnAction(event -> cadastrarNotas());
        
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
        btnVoltar.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnVoltar));
    }

    public void carregarDadosTabela(){
        try {
            AlunoCursoDao alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
            List<AlunoCurso> alunosNoCurso = alunoCursoDao.consultarAlunosCursosPorCurso(curso.getId());
            
            ObservableList<AlunoCurso> alunosObservable = FXCollections.observableArrayList(alunosNoCurso);
            tabelaTurma.setItems(alunosObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarNotas(){
        // Obtenha o aluno selecionado na tabela
        AlunoCurso alunoCursoSelecionado = tabelaTurma.getSelectionModel().getSelectedItem();

        // Verificação sobre qual campo tem dados

        if (alunoCursoSelecionado != null) {
            // Obtenha as notas digitadas nos campos de texto
            double nota1 = Double.parseDouble(inputNota1.getText());
            double nota2 = Double.parseDouble(inputNota2.getText());
            double nota3 = Double.parseDouble(inputNota3.getText());
            

            AlunoCursoDao alunoCursoDao;
            try {
                alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
                alunoCursoDao.cadastrarNotas(alunoCursoSelecionado.getAluno().getId(), curso.getId(), nota1, nota2, nota3);
            } catch (SQLException e) {
                
                e.printStackTrace();
            }

            // Atualize a tabela após a inserção da nota
            carregarDadosTabela();

            // Limpe os campos de entrada após a inserção
            inputNota3.clear();

            Sistema.exibirPopupSucesso("Notas cadastradas com sucesso!");
        } else {
            Sistema.exibirPopupErro("Nenhum aluno selecionado na tabela.");
        }
    }

    public void tornarVisivelInputNota1() {
        if (inputNota1.isVisible() && !inputNota1.isDisabled()) {
            // Se o campo está visível e habilitado, torne-o invisível e desabilitado
            inputNota1.setVisible(false);
            inputNota1.setDisable(true);
        } else {
            // Senão, torne-o visível e habilitado
            inputNota1.setVisible(true);
            inputNota1.setDisable(false);
        }
        
        // Verifica se o botão cadastrar está desabilitado e o torna visível e habilitado
        if (btnCadastrar.isDisabled()) {
            btnCadastrar.setVisible(true);
            btnCadastrar.setDisable(false);
        }
    }
}

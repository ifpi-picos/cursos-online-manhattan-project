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
            AlunoCursoDao alunoCursoDao;

            Double nota1 = inputNota1.getText().isEmpty() ? null : Double.parseDouble(inputNota1.getText());
            Double nota2 = inputNota2.getText().isEmpty() ? null : Double.parseDouble(inputNota2.getText());
            Double nota3 = inputNota3.getText().isEmpty() ? null : Double.parseDouble(inputNota3.getText());


            // Antes de cadastrar as notas, verifique se os dados estão corretos
            if (nota1 != null && (nota1 < 0 || nota1 > 10)) {
                Sistema.exibirPopupErro("Espaço vazio ou nota inválida");
                return;
            } else if (nota2 != null && (nota2 < 0 || nota2 > 10)) {
                Sistema.exibirPopupErro("A nota 2 deve estar entre 0 e 10.");
                return;
            } else if (nota3 != null && (nota3 < 0 || nota3 > 10)) {
                Sistema.exibirPopupErro("A nota 3 deve estar entre 0 e 10.");
                return;
            }

            // Se tá tudo certo, cadastre as notas individualmente
            // Cadastrar a nota 1
            if (nota1 != null) {
                try {
                    alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
                    alunoCursoDao.inserirPrimeiraNota(alunoCursoSelecionado.getAluno().getId(), curso.getId(), nota1);
                    inputNota1.clear();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            } else if (nota2 != null) {
                try {
                    alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
                    alunoCursoDao.inserirSegundaNota(alunoCursoSelecionado.getAluno().getId(), curso.getId(), nota2);
                    inputNota2.clear();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            } else if (nota3 != null) {
                try {
                    alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
                    alunoCursoDao.inserirTerceiraNota(alunoCursoSelecionado.getAluno().getId(), curso.getId(), nota3);
                    inputNota3.clear();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            }

            // try {
            //     alunoCursoDao = new AlunoCursoDao(Conexao.getConnection());
            //     alunoCursoDao.cadastrarNotas(alunoCursoSelecionado.getAluno().getId(), curso.getId(), nota1, nota2, nota3);
            // } catch (SQLException e) {
                
            //     e.printStackTrace();
            // }

            // Atualize a tabela após a inserção da nota
            carregarDadosTabela();

            // Limpe os campos de entrada após a inserção
            // inputNota1.clear();
            // inputNota2.clear();
            // inputNota3.clear();

            Sistema.exibirPopupSucesso("Notas cadastradas com sucesso!");
        } else {
            Sistema.exibirPopupErro("Nenhum aluno selecionado na tabela.");
        }
    }

    
}

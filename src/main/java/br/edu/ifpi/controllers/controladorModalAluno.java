package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoCursoDao;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.AlunoCurso;
import br.edu.ifpi.entities.Curso;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class controladorModalAluno implements Initializable{

    @FXML
    private TableColumn<AlunoCurso, Double> media;

    @FXML
    private AnchorPane nodalBackground;

    @FXML
    private TableView<AlunoCurso> tabelaNotas;

    @FXML
    private TableColumn<AlunoCurso, Double> nota1;

    @FXML
    private TableColumn<AlunoCurso, Double> nota2;

    @FXML
    private TableColumn<AlunoCurso, Double> nota3;

    private Curso curso;
    private AlunoCurso alunoCurso;

    public void inicializar(Curso curso){
        this.curso = curso;
        carregarListaNotas();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        nota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        nota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        nota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));

        // Adicionando uma coluna customizada para exibir a média
        media.setCellValueFactory(cellData -> {
            AlunoCurso alunoCurso = cellData.getValue();
            DoubleProperty mediaProperty = new SimpleDoubleProperty(alunoCurso.calcularMedia());
            return mediaProperty.asObject();
        });

        // Configurando o formato da célula para exibir a média formatada
        media.setCellFactory(column -> new TableCell<AlunoCurso, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f", item)); // Formatando para exibir duas casas decimais
                }
            }
        });
    }

    private void carregarListaNotas() {
        Connection connection = null;
        try {
            connection = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AlunoDao alunoDao = new AlunoDao(connection);
        Aluno aluno = alunoDao.consultarPorNomeEmail(SessaoUsuario.getNomeUsuario(), SessaoUsuario.getEmailUsuario());
        AlunoCursoDao alunoCursoDao = new AlunoCursoDao(connection);
        System.out.println("Aluno: " + aluno);
        System.out.println("Curso: " + curso);
        double[] notas = alunoCursoDao.consultarNotaPorAlunoECurso(aluno.getId(), curso.getId());
        alunoCurso = new AlunoCurso(aluno, curso);
        alunoCurso.setNota1(notas[0]);
        alunoCurso.setNota2(notas[1]);
        alunoCurso.setNota3(notas[2]);
        // Adicionando a instância à tabela
        tabelaNotas.getItems().add(alunoCurso);
    }

}

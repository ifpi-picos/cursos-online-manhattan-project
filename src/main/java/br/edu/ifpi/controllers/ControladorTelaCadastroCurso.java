package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.CursoDao;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.enums.StatusCurso;

public class ControladorTelaCadastroCurso implements Initializable {

    @FXML
    private AnchorPane AdmCadastroCurso;

    @FXML
    private Button Alunos;

    @FXML
    private TextField CargaHoraria;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button Home;

    @FXML
    private TextField NomeCurso;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private RadioButton aberto;

    @FXML
    private AnchorPane barraLateral;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private RadioButton fechado;

    @FXML
    private AnchorPane formulario;

    @FXML
    private ToggleGroup status;

    CursoDao BDCurso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura ações para os botões da barra lateral
        Home.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Home));
        Cursos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", Cursos));
        Professores.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", Professores));
        Alunos.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoAlunos.fxml", Alunos));
        Configuracao.setOnAction(event -> sistema.trocarCena("/fxml/telaMainAdm.fxml", Configuracao));
        Sair.setOnAction(event -> sistema.trocarCena("/fxml/telaLogIn.fxml",Sair));

        //configurando botões de interação da pagina de cadastro
        btnVoltar.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoCurso.fxml", btnVoltar));
        
        btnCadastrar.setOnAction(event -> cadastrarCurso());
    }

    private void cadastrarCurso(){
        // Criar uma instância de Connection usando a classe Conexao
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }
        // Chame o construtor de CursoDao passando a conexão como argumento
        BDCurso = new CursoDao(conexao);
        
        String nome = NomeCurso.getText();
        String cargaHoraria = CargaHoraria.getText();
        StatusCurso status;

        if(aberto.isSelected()){ 
            status = StatusCurso.ABERTO;
        }else{
            status = StatusCurso.FECHADO;
        }

        if (nome != null && !nome.isEmpty() && cargaHoraria != null && !cargaHoraria.isEmpty()) {
            CargaHoraria.clear();
            NomeCurso.clear();
            Curso curso = new Curso(nome, status, cargaHoraria);
            BDCurso.cadastrar(curso);
        } else {
            CargaHoraria.clear();
            NomeCurso.clear();
            exibirPopupErro(); ///emite mensagem de erro caso exista um campo vazio
        }

    }

    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("NÃO TA PRONTO MEU CHAPA");
        alert.showAndWait();
    }
    

}

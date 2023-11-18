package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entidades.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorCadastroProfessor implements Initializable{

    @FXML
    private Button Alunos;

    @FXML
    private Button Configuracao;

    @FXML
    private Button Cursos;

    @FXML
    private Button Home;

    @FXML
    private Button Professores;

    @FXML
    private Button Sair;

    @FXML
    private AnchorPane barraLateral;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField email;

    @FXML
    private TextField nome;

    @FXML
    private Button voltar;

    ProfessorDao BDprof;

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
        voltar.setOnAction(event -> sistema.trocarCena("/fxml/telaGerenciamentoProf.fxml", voltar));

        cadastrar.setOnAction(event -> gerarProfessor());
    }

    public void gerarProfessor(){

        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }
        BDprof = new ProfessorDao(conexao);
        String nomeProf = nome.getText();
        String emailProf = email.getText();

        if (sistema.validarEmail(emailProf)) {
            System.out.println("E-mail válido");
        } else {
            exibirPopupErro(); ///emite mensagem de erro caso email seja invalido
        }

        if (nomeProf != null && !nomeProf.isEmpty()) {
            nome.clear();
            email.clear();
            Professor professor = new Professor(nomeProf, emailProf);
            BDprof.cadastrar(professor);

        } else {
            nome.clear();
            email.clear();
            exibirPopupErro(); ///emite mensagem de erro caso exista um campo vazio
        }

    }

    //Função para emitir alerta de erro na tela 
    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText("Dados não preenchidos ou inválidos");
        alert.showAndWait();
    }


}

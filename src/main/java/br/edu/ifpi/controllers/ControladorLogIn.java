package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorLogIn implements Initializable{

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private AnchorPane formsLogIn;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCadastrar.setOnAction(event -> Sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar));
        btnEntrar.setOnAction(event -> Autenticar());
    }

    private void Autenticar(){
        String nome = inputNome.getText();
        String email = inputEmail.getText();
        
        if (!Sistema.verificarCampos(nome, email)) {
            return;
        }

        if (!Sistema.validarEmail(email)) {
            Sistema.exibirPopupErro("Formato de e-mail inválido.");
            return;
        }

        try {
            AlunoDao alunoDao = new AlunoDao(Conexao.getConnection());
            ProfessorDao professorDao = new ProfessorDao(Conexao.getConnection());

            if(alunoDao.verificarEmailExistente(email)){
            SessaoUsuario.setEmailUsuario(email);
            SessaoUsuario.setNomeUsuario(nome);
            SessaoUsuario.setTipoUsuario("ALUNO");
            Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnEntrar);

        }else if (professorDao.verificarEmailExistente(email)) {
            SessaoUsuario.setEmailUsuario(email);
            SessaoUsuario.setNomeUsuario(nome);
            SessaoUsuario.setTipoUsuario("PROFESSOR");
            Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml", btnEntrar);

        } else {
           Sistema.exibirPopupErro("Usuário Inexistente!"); 
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
}
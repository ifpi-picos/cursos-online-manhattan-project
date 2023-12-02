package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorLogIn implements Initializable, SessaoController{

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


    private SessaoDao sessaoDao;

    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCadastrar.setOnAction(event -> Sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar, sessaoDao));
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

        if(sessaoDao.alunoDao.verificarEmailExistente(email)){
            SessaoUsuario.setEmailUsuario(email);
            SessaoUsuario.setNomeUsuario(nome);
            SessaoUsuario.setTipoUsuario("ALUNO");
            Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnEntrar, sessaoDao);

        }else if (sessaoDao.professorDao.verificarEmailExistente(email)) {
            SessaoUsuario.setEmailUsuario(email);
            SessaoUsuario.setNomeUsuario(nome);
            SessaoUsuario.setTipoUsuario("PROFESSOR");
            Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml", btnEntrar, sessaoDao);

        } else {
           Sistema.exibirPopupErro("Usuário Inexistente!"); 
        }
    }
      
}
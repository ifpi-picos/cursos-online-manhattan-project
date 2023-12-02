package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Professor;
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
        // String nome;
        // String email;
        // verificarCampos();
        // email = inputEmail.getText();
        // nome = inputNome.getText();

        String nome = inputNome.getText();
        String email = inputEmail.getText();

        if (Sistema.verificarCampos(nome, email) && Sistema.validarEmail(email)){
            Connection connection = sessaoDao.getConnection();
            
            AlunoDao alunoDao = new AlunoDao(connection);
            ProfessorDao professorDao = new ProfessorDao(connection);

            Aluno aluno = alunoDao.consultarPorEmail(email);
            Professor professor = professorDao.consultarPorEmail(email);

            if (aluno != null){
                
                Sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnEntrar, sessaoDao);
            } else if (professor != null){
                
                Sistema.trocarCena("/fxml/telaInicialProf.fxml", btnEntrar, sessaoDao);
            } else {
                Sistema.exibirPopupErro("Usuário não encontrado.");
            }
        }           
    }
    
    // Função que verifica se os campos foram preenchidos corretamente (nome e email em estrutura válida)
    private void verificarCampos() {
        if (!Sistema.verificarCampos(inputNome.getText(), inputEmail.getText())) {
            Sistema.exibirPopupErro("Por favor, preencha todos os campos.");
            return;
        }
    
        if (!Sistema.validarEmail(inputEmail.getText())) {
            Sistema.exibirPopupErro("O formato do e-mail é inválido.");
            inputNome.clear();
            inputEmail.clear();
            return;
        }
    }
      
}
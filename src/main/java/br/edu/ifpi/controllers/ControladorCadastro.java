package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoController;
import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControladorCadastro implements Initializable, SessaoController{

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private ToggleGroup escolha;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;

    @FXML
    private RadioButton radioAluno;

    @FXML
    private RadioButton radioProfessor;

    private SessaoDao sessaoDao;

    public void getSessao (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVoltar.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnVoltar,sessaoDao));
        btnCadastrar.setOnAction(event-> gerarCadastro());
    }

    public boolean verificarEmailExistente(String email) {
        boolean alunoEmailExistente = sessaoDao.alunoDao.verificarEmailExistente(email);
        boolean professorEmailExistente = sessaoDao.professorDao.verificarEmailExistente(email);
        
        if (alunoEmailExistente || professorEmailExistente) {
            Sistema.exibirPopupErro("E-mail já cadastrado. Por favor, escolha outro e-mail.");
            return true; 
        }
        return false; 
    }

    public void gerarCadastro() {
        String nome = inputNome.getText();
        String email = inputEmail.getText();
        // Verifica se os campos estão preenchidos
        if (!Sistema.verificarCampos(nome, email)) {
            return;
        }

        // Valida o formato do e-mail
        if (!Sistema.validarEmail(email)) {
            Sistema.exibirPopupErro("Formato de e-mail inválido.");
            return;
        }

        if (verificarEmailExistente(email)) {
            return;
        }
        
        // Verifica se um dos botões de rádio está marcado
        if (!(radioAluno.isSelected() || radioProfessor.isSelected())) {
            Sistema.exibirPopupErro("Por favor, selecione Aluno ou Professor.");
            return;
        }

        // Se tudo estiver correto, exibe o popup de sucesso
        Sistema.exibirPopupSucesso("Cadastro realizado com sucesso!");

    }
}

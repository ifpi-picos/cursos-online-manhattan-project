package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.Sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ControladorCadastro implements Initializable{

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVoltar.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnVoltar));
        btnCadastrar.setOnAction(event-> gerarCadastro());
    }

    public boolean verificarEmailExistente(String email) {
        try {
            AlunoDao alunoDao = new AlunoDao(Conexao.getConnection());
            ProfessorDao professorDao = new ProfessorDao(Conexao.getConnection());
            boolean alunoEmailExistente = alunoDao.verificarEmailExistente(email);
            boolean professorEmailExistente = professorDao.verificarEmailExistente(email);
            if (alunoEmailExistente || professorEmailExistente) {
                Sistema.exibirPopupErro("E-mail já cadastrado. Por favor, escolha outro e-mail.");
                return true; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; 
    }

    public void gerarCadastro() {
        String nome = inputNome.getText();
        String email = inputEmail.getText();
        
        if (!Sistema.verificarCampos(nome, email)) {
            return;
        }

        if (!Sistema.validarEmail(email)) {
            Sistema.exibirPopupErro("Formato de e-mail inválido.");
            return;
        }

        if (verificarEmailExistente(email)) {
            return;
        }

        if (!(radioAluno.isSelected() || radioProfessor.isSelected())) {
            Sistema.exibirPopupErro("Por favor, selecione Aluno ou Professor.");
            return;
        }

        try {
            AlunoDao alunoDao = new AlunoDao(Conexao.getConnection());
            ProfessorDao professorDao = new ProfessorDao(Conexao.getConnection());

            if (radioAluno.isSelected()) {
                Aluno aluno = new Aluno(nome, email);
                alunoDao.cadastrar(aluno);
                limparCampos();
                Sistema.exibirPopupSucesso("Cadastro realizado com sucesso!");
            }else{
                Professor professor = new Professor(nome, email);
                professorDao.cadastrar(professor);
                limparCampos();
                Sistema.exibirPopupSucesso("Cadastro realizado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limparCampos() {
        inputNome.clear();
        inputEmail.clear();
        radioAluno.setSelected(false);
        radioProfessor.setSelected(false);
    }
}

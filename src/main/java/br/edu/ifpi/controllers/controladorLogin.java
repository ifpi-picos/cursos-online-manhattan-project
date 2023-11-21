package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
import br.edu.ifpi.dao.AlunoDao;
import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.dao.ProfessorDao;
import br.edu.ifpi.entities.Aluno;
import br.edu.ifpi.entities.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controladorLogin implements Initializable {

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
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<Professor> professores = carregarProfessor();
        List<Aluno> alunos = carregarAluno();
        btnCadastrar.setOnAction(event -> sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar));
        btnEntrar.setOnAction(event -> autenticar(professores, alunos));

    }

    // Função para carregar uma lista de professores


    public void autenticar(List<Professor> professores, List<Aluno> alunos){
        String nome = inputNome.getText();
        String email = inputEmail.getText();
    
        for (Professor professor : professores) {
            if (professor.getNome().equals(nome) && professor.getEmail().equals(email)) {
                System.out.println("Professor encontrado");
        
                System.out.println("Nome: " + professor.getNome());
                System.out.println("Email: " + professor.getEmail());

                sistema.trocarCena("/fxml/telaInicialProf.fxml", btnEntrar);
            } else {
                System.out.println("Professor não encontrado");
            }
        }

    }
    
    //Carregar professor
    public List<Professor> carregarProfessor (){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }

        ProfessorDao professorDao = new ProfessorDao(conexao);

        List<Professor> professores = professorDao.consultarTodos();

        return professores;
    }
    
    //Carregar aluno
    public List<Aluno> carregarAluno (){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }

        AlunoDao alunoDao = new AlunoDao(conexao);

        List<Aluno> alunos = alunoDao.consultarTodos();

        return alunos;
    }
}
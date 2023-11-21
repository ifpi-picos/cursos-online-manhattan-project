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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        
        btnCadastrar.setOnAction(event -> sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar));
        btnEntrar.setOnAction(event -> autenticar());

    }

    // Função para carregar uma lista de professores


    public void autenticar(){
        String nome = inputNome.getText();
        String email = inputEmail.getText();

        System.out.println("Nome" + nome);
        System.out.println("Email" + email);
        
        if(verificarProfessor(nome, email) == true){
            // Carregar o FXML da nova cena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaInicialProf.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Obter a referência do palco (Stage)
                Stage stage = (Stage) formsLogIn.getScene().getWindow();

                // Mudar para a nova cena
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(verificarAluno(nome, email) == true ) {
             // Carregar o FXML da outra cena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaInicialAluno.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root);

                // Obter a referência do palco (Stage)
                Stage stage = (Stage) formsLogIn.getScene().getWindow();

                // Mudar para a nova cena
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 

    }
    
    //verificar professor
    public boolean verificarProfessor (String nome , String email){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        ProfessorDao professorDao = new ProfessorDao(conexao);
        List<Professor> professores = professorDao.consultarTodos();

        for (Professor professor : professores) {
            if (professor.getNome().equals(nome) && professor.getEmail().equals(email)) {
                // Match found
                return true;
            }
        }

        // No match found
        return false;
    }
    
    //verificar aluno
    public boolean verificarAluno (String nome, String email){
        Connection conexao = null;
        try {
            conexao = Conexao.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        AlunoDao alunoDao = new AlunoDao(conexao);
        List<Aluno> alunos = alunoDao.consultarAutenticar();

        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome) && aluno.getEmail().equals(email)) {
                // Match found
                return true;
            }
        }
    
        // No match found
        return false;

    }
}
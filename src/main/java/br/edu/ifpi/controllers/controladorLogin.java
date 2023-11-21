package br.edu.ifpi.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.edu.ifpi.sistema;
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
        btnCadastrar.setOnAction(event -> sistema.trocarCena("/fxml/cadastro.fxml", btnCadastrar));

    }

    public void autenticar(){
        String nome = inputNome.getText();
        String email = inputEmail.getText();
    
        // Estabelecer conexão com o banco de dados
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://db.ijhnuifhmrhpcwvnlezj.supabase.co:5432/postgres?sslmode=require", "postgres", "ifpi_bd2023");
    
            // Consulta SQL para verificar os dados na tabela de professores
            String sqlProfessores = "SELECT * FROM professores WHERE nome = ? AND email = ?";
            stmt = conn.prepareStatement(sqlProfessores);
            stmt.setString(1, nome);
            stmt.setString(2, email);
    
            rs = stmt.executeQuery();
    
            // Se a consulta retornar um resultado, o usuário é autenticado como professor
            if (rs.next()) {
                sistema.trocarCena("/fxml/homeProfessor.fxml", btnEntrar);
            } else {
                // Se não, tentar autenticar como aluno
                stmt.close();
                rs.close();
    
                String sqlAlunos = "SELECT * FROM alunos WHERE nome = ? AND email = ?";
                stmt = conn.prepareStatement(sqlAlunos);
                stmt.setString(1, nome);
                stmt.setString(2, email);
    
                rs = stmt.executeQuery();
    
                // Se a consulta retornar um resultado, o usuário é autenticado como aluno
                if (rs.next()) {
                    sistema.trocarCena("/fxml/telaInicialAluno.fxml", btnEntrar);
                } else {
                    // Caso contrário, mostrar uma mensagem de erro
                    System.out.println("Nome de usuário ou email incorretos");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
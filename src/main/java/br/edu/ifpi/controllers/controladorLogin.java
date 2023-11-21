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
    
        // Substitua as informações do banco de dados conforme necessário
        String url = "jdbc:postgresql://db.ijhnuifhmrhpcwvnlezj.supabase.co:5432/postgres?sslmode=require";
        String usuarioBanco = "postgres";
        String senhaBanco = "ifpi_bd2023";

        // SQL para consultar se o aluno existe
        String sql = "SELECT nome, email FROM alunos WHERE nome = ? AND email = ?";

        try (
            // Criação da conexão com o banco de dados
            Connection conexao = DriverManager.getConnection(url, usuarioBanco, senhaBanco);
            
            // Preparação da consulta SQL
            PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            // Define os parâmetros da consulta
            stmt.setString(1, nome);
            stmt.setString(2, email);

            // Executa a consulta
            ResultSet resultado = stmt.executeQuery();

            // Verifica se o aluno foi autenticado
            if (resultado.next()) {
                // Aluno autenticado
                sistema.trocarCena("/fxml/telaInicial.fxml", btnEntrar);
                String nomeAutenticado = resultado.getString("nome");
                String emailAutenticado = resultado.getString("email");

                System.out.println("Aluno autenticado:");
                System.out.println("Nome: " + nomeAutenticado);
                System.out.println("Email: " + emailAutenticado);
            } else {
                // Aluno não autenticado
                System.out.println("Aluno não autenticado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
}
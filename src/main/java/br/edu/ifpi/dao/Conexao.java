package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    //Dados do servidor local do Matheus, para usar outro basta alterar os dados
    private static final String URL = "jdbc:postgresql://localhost:1954/manhattan-test";  
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1954";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // Connection conexao = null;
    //Dados do servidor local do Matheus, para usar outro basta alterar os dados
    private static final String URL = "postgres://jvbamwlf:8w9doiYjroMsj7-f2smcWCl3rywvMv8f@isabelle.db.elephantsql.com/jvbamwlf";  
    private static final String USUARIO = "jvbamwlf";
    private static final String SENHA = "8w9doiYjroMsj7-f2smcWCl3rywvMv8f";

   // Método para abrir a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    public static void fecharConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Trate a exceção conforme necessário
            }
        }
    }
    
}


            
        
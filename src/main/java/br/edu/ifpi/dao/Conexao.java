package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
  public static Connection getConexao() {
    try {
      Connection conexao = DriverManager
          .getConnection("", "postgres", "12345"); // Informações do banco de dados
      return conexao;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
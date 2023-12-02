package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://db.ijhnuifhmrhpcwvnlezj.supabase.co:5432/postgres?sslmode=require";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ifpi_bd2023";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void closeConnection() {
        try {
            DriverManager.getConnection(URL, USERNAME, PASSWORD).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static final String URL = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/jvbamwlf";  
    private static final String USUARIO = "jvbamwlf";
    private static final String SENHA = "8w9doiYjroMsj7-f2smcWCl3rywvMv8f";

    // private static final String URL = "jdbc:postgresql://localhost:5432/ManhattanBD";  
    // private static final String USUARIO = "postgres";
    // private static final String SENHA   = "oppai69";

    

    // Método para abrir a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
        return connection;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Trate a exceção conforme necessário
            }
        }
    }

    // Método para criar tabelas se não existirem
    public static void criarTabelasSeNecessario(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            // Tabela Professores
            if (!tabelaExiste(statement, "Professores")) {
                statement.executeUpdate("CREATE TABLE Professores (id SERIAL PRIMARY KEY, nome VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL);");
                System.out.println("Tabela Professores criada.");
            }

            // Tabela Cursos
            if (!tabelaExiste(statement, "Cursos")) {
                statement.executeUpdate("CREATE TABLE Cursos (id SERIAL PRIMARY KEY, nome VARCHAR(255) NOT NULL, StatusCurso VARCHAR(255) NOT NULL, carga_Horaria INT NOT NULL);");
                System.out.println("Tabela Cursos criada.");
            }

            // Tabela Alunos
            if (!tabelaExiste(statement, "Alunos")) {
                statement.executeUpdate("CREATE TABLE Alunos (id SERIAL PRIMARY KEY, nome VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL);");
                System.out.println("Tabela Alunos criada.");
            }

            // Tabela Professor_Curso
            if (!tabelaExiste(statement, "Professor_Curso")) {
                statement.executeUpdate("CREATE TABLE Professor_Curso (professor_id INT REFERENCES Professores(id), curso_id INT REFERENCES Cursos(id), PRIMARY KEY (professor_id, curso_id));");
                System.out.println("Tabela Professor_Curso criada.");
            }

            // Tabela Aluno_Curso
            if (!tabelaExiste(statement, "Aluno_Curso")) {
                statement.executeUpdate("CREATE TABLE Aluno_Curso (aluno_id INT REFERENCES Alunos(id), curso_id INT REFERENCES Cursos(id), nota Array PRECISION, PRIMARY KEY (aluno_id, curso_id));");
                System.out.println("Tabela Aluno_Curso criada.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção conforme necessário
        }
    }

    // Função para verificar a existência de uma tabela
    private static boolean tabelaExiste(Statement statement, String nomeTabela) throws SQLException {
        try {
            statement.execute("SELECT to_regclass('" + nomeTabela + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
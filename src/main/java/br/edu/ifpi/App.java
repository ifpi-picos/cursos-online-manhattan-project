package br.edu.ifpi;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.ifpi.dao.Conexao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Connection connection = null;

        
        try {
            // Abrir a conexão
            connection = Conexao.getConnection();
            System.out.println("Conexão aberta.");

            // Cria tabelas se necessário
            Conexao.criarTabelasSeNecessario(connection);
            System.out.println("Tabelas criadas.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão, mesmo se ocorrer uma exceção
            Conexao.fecharConexao(connection);
            System.out.println("Conexão fechada.");
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaMainAdm.fxml"));
        String css = this.getClass().getResource("/css/styles.css").toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

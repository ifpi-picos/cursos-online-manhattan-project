package br.edu.ifpi;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application{


    @Override
    public void start(Stage stage) throws Exception {

        // Criar um layout simples usando um StackPane
        StackPane root = new StackPane();

        // Configurar a cena
        Scene scene = new Scene(root, 600, 400);
        URL resourceUrl = getClass().getResource("/fxml/login.fxml");
        System.out.println("Resource URL: " + resourceUrl);

        // Parent root = FXMLLoader.load(resourceUrl);
        // Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

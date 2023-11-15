package br.edu.ifpi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class System {

    public static void trocarCena(String caminhoFXML, Button botao) {
        try {
            // Carrega o arquivo FXML
            FXMLLoader loader = new FXMLLoader(System.class.getResource(caminhoFXML));
            Parent novaCena = loader.load();

            // Obtém o palco principal a partir do botão clicado
            Stage palco = (Stage) botao.getScene().getWindow();

            // Configura a nova cena
            Scene novaScene = new Scene(novaCena);
            palco.setScene(novaScene);
            palco.show();
        } catch (IOException e) {
            e.printStackTrace(); // Lida com exceções ao carregar o FXML
        }
    }
}

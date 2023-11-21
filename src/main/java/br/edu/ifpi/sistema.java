package br.edu.ifpi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sistema {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static void trocarCena(String caminhoFXML, Button botao) {
        try {
            // Carrega o arquivo FXML
            FXMLLoader loader = new FXMLLoader(sistema.class.getResource(caminhoFXML));
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

    public static boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void exibirPopupErro(String mensagemErro) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagemErro);
        alert.showAndWait();
    }

    public void exibirPopupSucesso(String mensagemSucesso) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagemSucesso);
        alert.showAndWait();
    }
}
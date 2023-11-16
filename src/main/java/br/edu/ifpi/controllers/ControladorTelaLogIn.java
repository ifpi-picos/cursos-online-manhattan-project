package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControladorTelaLogIn implements Initializable {

    @FXML
    private AnchorPane BackgroundImg;

    @FXML
    private AnchorPane FormularioLogIn;

    @FXML
    private AnchorPane background;

    @FXML
    private Button botaoEntrar;

    @FXML
    private PasswordField inputSenhaLogIn;

    @FXML
    private TextField inputUsuarioLogIn;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputSenhaLogIn.clear();
        inputUsuarioLogIn.clear();
        botaoEntrar.setOnAction(event -> acessarApp());
    }

    public void acessarApp(){
            String usuario = inputUsuarioLogIn.getText();
            String senha = inputSenhaLogIn.getText();
            System.out.println("Usuario Digitado: " + usuario);
            System.out.println("Senha Digitada: " + senha );
            validacao(usuario, senha);

    }

    public void validacao (String usuario, String senha){
        if(usuario.equals("adm") && senha.equals("1234")){
            System.out.println("Logado");
            exibirNovaPagina();
        } else {
            inputSenhaLogIn.clear();
            inputUsuarioLogIn.clear();
            exibirPopupErro();
        }
    }

    // Exibe mensagem de erro ao tentar acessar com credenciais não existentes.
    private void exibirPopupErro() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de Autenticação");
        alert.setHeaderText(null);
        alert.setContentText("Usuário ou senha incorretos ou inexistentes.");

        alert.showAndWait();
    }

    private void exibirNovaPagina() {
        try {
            // Carregar a nova página a partir do arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaMainAdm.fxml"));
            Parent novaPagina = loader.load();

            // Criar uma nova cena com a nova página
            Scene novaCena = new Scene(novaPagina);

            // Obter o palco atual
            Stage palcoAtual = (Stage) botaoEntrar.getScene().getWindow();

            // Definir a nova cena no palco
            palcoAtual.setScene(novaCena);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

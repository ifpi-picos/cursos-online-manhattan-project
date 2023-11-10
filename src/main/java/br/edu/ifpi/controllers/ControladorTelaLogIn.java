package br.edu.ifpi.controllers;

import java.io.IOException;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorTelaLogIn {
    @FXML
    private AnchorPane BackgroundImg;

    @FXML
    private ImageView LogoManhattan;

    @FXML
    private AnchorPane background;

    @FXML
    private Button botaoEntrar;

    @FXML
    private PasswordField inputSenhaLogIn;

    @FXML
    private TextField inputUsuarioLogIn;

    @FXML
    private Text logInText;
    

    public void initialize() {
        botaoEntrar.setOnAction(e -> {
            getInputEntrar();
            System.out.println("Botão Entrar clicado!");
        });
    }

    public void getInputEntrar (){
        String usuario = inputUsuarioLogIn.getText();
        String senha = inputSenhaLogIn.getText();

        System.out.println("User:" + usuario);
        System.out.println("Password:" +senha);

        inputSenhaLogIn.setText(""); 
        inputUsuarioLogIn.setText("");                  

        if ("adm".equals(usuario) && "123".equals(senha)) {
            carregarTelaMainAdm();
        } else {
            darFeedbackVisualBotao();
        }
    }

    private void darFeedbackVisualBotao() {
        // Mudar temporariamente a cor de fundo do botão ao ser clicado
        PseudoClass pressed = PseudoClass.getPseudoClass("pressed");

        // Aplica a pseudoclasse quando o botão é pressionado
        botaoEntrar.pseudoClassStateChanged(pressed, true);

        // Agendar a remoção da pseudoclasse após um intervalo de tempo
        botaoEntrar.getScene().getWindow().getScene().getRoot().requestFocus();
    }

    private void carregarTelaMainAdm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaMainAdm.fxml"));
            ControladorTelaMainAdm controladorTelaMainAdm = loader.getController();
            Parent novaTela = loader.load();

            Stage stage = (Stage) botaoEntrar.getScene().getWindow();
            Scene scene = new Scene(novaTela);

            // Substitui a tela atual pela nova tela
            stage.setScene(scene);
            stage.show();

            // Se necessário, você pode realizar outras operações no controlador da nova tela
            controladorTelaMainAdm.initialize();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


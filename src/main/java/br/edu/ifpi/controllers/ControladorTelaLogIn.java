package br.edu.ifpi.controllers;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

        darFeedbackVisualBotao();
    }

    private void darFeedbackVisualBotao() {
        // Mudar temporariamente a cor de fundo do botão ao ser clicado
        PseudoClass pressed = PseudoClass.getPseudoClass("pressed");

        // Aplica a pseudoclasse quando o botão é pressionado
        botaoEntrar.pseudoClassStateChanged(pressed, true);

        // Agendar a remoção da pseudoclasse após um intervalo de tempo
        botaoEntrar.getScene().getWindow().getScene().getRoot().requestFocus();
    }
}


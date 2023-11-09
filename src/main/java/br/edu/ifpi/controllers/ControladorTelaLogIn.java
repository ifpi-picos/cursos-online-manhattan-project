package br.edu.ifpi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    

    public void getInputEntrar (){
        botaoEntrar.setOnAction(e -> {
        String usuario = inputUsuarioLogIn.getText();
        String senha = inputSenhaLogIn.getText();

        System.out.println("User:" + usuario);
        System.out.println("Password:" +senha);

        inputSenhaLogIn.setText(""); 
       inputUsuarioLogIn.setText("");
        });
    }
}


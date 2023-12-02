package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoDao;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControladorLogIn implements Initializable{

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnEntrar;

    @FXML
    private AnchorPane formsLogIn;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputNome;


    private SessaoDao sessaoDao;

    public ControladorLogIn (SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    public void getReady(){
        ControladorCadastro controladorCadastro = new ControladorCadastro(sessaoDao);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }



}

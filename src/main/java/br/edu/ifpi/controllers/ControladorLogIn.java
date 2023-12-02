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
        // Condicional para verificar se os campos foram preenchidos corretamente
        btnEntrar.setOnAction(event -> {
            if (verificarCampos()) {
                sessaoDao.criarSessao(inputNome.getText(), inputEmail.getText());
                Sistema.trocarCena("/br/edu/ifpi/views/telaPrincipal.fxml", btnEntrar, new ControladorTelaPrincipal(sessaoDao));
            }
        });
        btnCadastrar.setOnAction(event -> Sistema.trocarCena(null, btnCadastrar, null));
        btnEntrar.setOnAction(event -> Sistema.trocarCena(null, btnEntrar, null));
    }


    // Função que verifica se os campos foram preenchidos corretamente (nome e email em estrutura válida)
    private boolean verificarCampos() {
        if (!Sistema.verificarCampos(inputNome.getText(), inputEmail.getText())) {
            Sistema.exibirPopupErro("Por favor, preencha todos os campos.");
            return false;
        }
    
        if (!Sistema.validarEmail(inputEmail.getText())) {
            Sistema.exibirPopupErro("O formato do e-mail é inválido.");
            inputNome.clear();
            inputEmail.clear();
            return false;
        }
        return true;
    }
    
}
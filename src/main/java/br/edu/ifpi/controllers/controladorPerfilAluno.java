package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControladorPerfilAluno implements Initializable {
   @FXML
    private Button btnCursos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnMeusCursos;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnVoltar;

    @FXML
    private Text textEmail;

    @FXML
    private Text textNome;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exibirDadosAluno();
        
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/cursosAluno.fxml", btnCursos));
        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasAluno/telaInicialAluno.fxml", btnCursos));
        btnPerfil.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/perfilAluno.fxml", btnPerfil));
        btnMeusCursos.setOnAction(event-> Sistema.trocarCena("/fxml/telasAluno/meusCursos.fxml", btnMeusCursos));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

    public void exibirDadosAluno() {
        textNome.setText(SessaoUsuario.getNomeUsuario());
        textEmail.setText(SessaoUsuario.getEmailUsuario());
    }
}

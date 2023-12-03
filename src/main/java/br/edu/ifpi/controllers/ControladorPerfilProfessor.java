package br.edu.ifpi.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifpi.SessaoUsuario;
import br.edu.ifpi.Sistema;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ControladorPerfilProfessor implements Initializable{
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
    private Text carregarEmail;

    @FXML
    private Text carregarNome;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exibirDadosProfessor();

        btnHome.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/telaInicialProf.fxml",btnHome));
        btnCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/gerenciarCursos.fxml", btnCursos));
        btnMeusCursos.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/MeusCursosProf.fxml", btnMeusCursos));
        btnPerfil.setOnAction(event -> Sistema.trocarCena("/fxml/telasProfessor/perfilProfessor.fxml", btnPerfil));
        btnSair.setOnAction(event -> Sistema.trocarCena("/fxml/login.fxml", btnSair));
    }

    public void exibirDadosProfessor() {
        carregarNome.setText(SessaoUsuario.getNomeUsuario());
        carregarEmail.setText(SessaoUsuario.getEmailUsuario());
    }
}

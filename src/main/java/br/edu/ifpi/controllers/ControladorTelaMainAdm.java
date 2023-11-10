package br.edu.ifpi.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ControladorTelaMainAdm {
    @FXML
    private AnchorPane Background;

    @FXML
    private Button alunosBotao;

    @FXML
    private Button configuracoesBotao;

    @FXML
    private Button cursosBotao;

    @FXML
    private Button homeBotao;

    @FXML
    private Button permissoesContasbotao;

    @FXML
    private Button professoresBotao;

    @FXML
    public void initialize() {
        cursosBotao.setOnAction(event -> trocarParaTelaCursos());
    }

    private void trocarParaTelaCursos() {
        try {
            // Carrega o controlador e a nova tela a partir do arquivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/telaCursosAdm.fxml"));
            ControladorTelaCursosAdm controladorTelaCursosAdm = loader.getController();
            Parent novaTela = loader.load();

            // Substitui a tela atual pela nova tela
            Background.getChildren().setAll(novaTela);

            // Se necessário, você pode realizar outras operações no controlador da nova tela
            controladorTelaCursosAdm.initialize(null, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

